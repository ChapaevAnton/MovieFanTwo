package com.w4ereT1ckRtB1tch.moviefan.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxRemoteMediator
import com.w4ereT1ckRtB1tch.moviefan.data.db.FilmDataBase
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.source.MoviesConfig
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbConfig
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbKey
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.model.FilmRemoteKeys
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Films
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@ExperimentalPagingApi
class FilmsRemoteMediatorImpl @Inject constructor(
    private val api: TmdbApi,
    private val mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
    private val dataBase: FilmDataBase
) : RxRemoteMediator<Int, Film>() {

    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, Film>
    ): Single<MediatorResult> {
        return Single.just(loadType).subscribeOn(Schedulers.io()).map {
            when (it) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevKey ?: INVALID_PAGE_INDEX
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextKey ?: INVALID_PAGE_INDEX
                }
            }
        }.flatMap { nextPageNumber ->
            // FIXME: 26.10.2021 MoviesConfig.Path.POPULAR_CATEGORY
            if (nextPageNumber == INVALID_PAGE_INDEX) {
                Single.just(MediatorResult.Success(endOfPaginationReached = true))
            } else {
                api.getFilms(
                    MoviesConfig.Path.POPULAR_CATEGORY,
                    TmdbKey.API_KEY_V3,
                    TmdbConfig.LANGUAGE_RU,
                    nextPageNumber
                ).map { filmsResponse ->
                    mapper.mapOfResponse(filmsResponse)
                }.map { films ->
                    insertToDataBase(nextPageNumber, loadType, films)
                }.map<MediatorResult> {
                    MediatorResult.Success(endOfPaginationReached = it.endOfPage)
                }.onErrorReturn { MediatorResult.Error(it) }
            }
        }
    }

    // FIXME: 25.10.2021 DEPRECATION
    @Suppress("DEPRECATION")
    private fun insertToDataBase(page: Int, loadType: LoadType, data: Films): Films {
        dataBase.beginTransaction()
        try {
            if (loadType == LoadType.REFRESH) {
                dataBase.filmRemoteKeysRxDao().clearRemoteKeys()
                dataBase.filmRxDao().clearFilms()
            }

            val prevKey = if (page == 1) null else page.minus(-1)
            val nextKey = if (data.endOfPage) null else page.plus(1)
            val keys = data.films.map {
                FilmRemoteKeys(filmId = it.filmId, prevKey = prevKey, nextKey = nextKey)
            }
            dataBase.filmRemoteKeysRxDao().insertAll(keys)
            dataBase.filmRxDao().insertAll(data.films)
            dataBase.setTransactionSuccessful()

        } finally {
            dataBase.endTransaction()
        }
        return data
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, Film>): FilmRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { film ->
            dataBase.filmRemoteKeysRxDao().remoteKeyByFilmId(film.filmId)
        }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, Film>): FilmRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { film ->
            dataBase.filmRemoteKeysRxDao().remoteKeyByFilmId(film.filmId)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Film>): FilmRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.filmId?.let { filmId ->
                dataBase.filmRemoteKeysRxDao().remoteKeyByFilmId(filmId)
            }
        }
    }

    companion object {
        const val INVALID_PAGE_INDEX = -1
        const val STARTING_PAGE_INDEX = 1
    }
}