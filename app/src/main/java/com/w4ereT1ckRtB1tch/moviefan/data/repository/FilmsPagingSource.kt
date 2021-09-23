package com.w4ereT1ckRtB1tch.moviefan.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbConfig
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbKey
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmsPagingSource @Inject constructor(
    private val api: TmdbApi,
    private val mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
) : RxPagingSource<Int, List<Film>>() {

    override fun getRefreshKey(state: PagingState<Int, List<Film>>): Int? {

        val anchorPosition: Int = state.anchorPosition ?: return null
        val anchorPage: LoadResult.Page<Int, List<Film>> =
            state.closestPageToPosition(anchorPosition) ?: return null

        val prevKey = anchorPage.prevKey
        if (prevKey != null) {
            return prevKey + 1
        }

        val nextKey = anchorPage.nextKey
        if (nextKey != null) {
            return nextKey - 1
        }

        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, List<Film>>> {
        val nextPageNumber = params.key ?: 1
        return api.getUpcomingFilms(TmdbKey.API_KEY_V3, TmdbConfig.LANGUAGE_RU, nextPageNumber)
            .subscribeOn(Schedulers.io()).map { toLoadResult(it) }
    }

    private fun toLoadResult(
        filmsResponse: FilmsResponse
    ): LoadResult<Int, List<Film>> {
        return LoadResult.Page(
            listOf(mapper.map(filmsResponse)),
            filmsResponse.page - 1,
            filmsResponse.page + 1
        )
    }

}