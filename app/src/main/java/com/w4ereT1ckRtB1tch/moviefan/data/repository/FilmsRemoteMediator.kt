package com.w4ereT1ckRtB1tch.moviefan.data.repository

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
import java.io.InvalidObjectException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class FilmsRemoteMediator @Inject constructor(
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
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                        //?: throw InvalidObjectException("Result is empty")
                    remoteKeys?.prevKey ?: INVALID_PAGE
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                        //?: throw InvalidObjectException("Result is empty")
                    remoteKeys?.nextKey ?: INVALID_PAGE
                }
            }
        }.flatMap { nextPageNumber ->
            if (nextPageNumber == INVALID_PAGE) {
                Single.just(MediatorResult.Success(endOfPaginationReached = true))
            } else {
                api.getFilms(
                    MoviesConfig.Path.POPULAR_CATEGORY,
                    TmdbKey.API_KEY_V3,
                    TmdbConfig.LANGUAGE_RU,
                    nextPageNumber
                ).map {
                    mapper.mapOfResponse(it)
                }.map<MediatorResult> {
                    // FIXME: 25.10.2021  val endOfPage = total == page
                    MediatorResult.Success(endOfPaginationReached = it.endOfPage)
                }.onErrorReturn { MediatorResult.Error(it) }
            }

        }
    }

    // TODO: 25.10.2021 added record database
    // FIXME: 25.10.2021 DEPRECATION
    @Suppress("DEPRECATION")
    private fun insertToDataBase(page: Int, loadType: LoadType, data: Films): Films {
        dataBase.beginTransaction()

        try {
            if (loadType == LoadType.REFRESH) {
                dataBase.filmRemoteKeysRxDao().clearRemoteKeys()
                dataBase.filmRxDao().clearFilms()
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (data.endOfPage) null else page + 1
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
        const val INVALID_PAGE = -1
    }
}