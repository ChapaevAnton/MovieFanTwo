package com.w4ereT1ckRtB1tch.moviefan.data.repository.paging

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProvider
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.PagingSourceConfig.INITIAL_PAGE_NUMBER
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbConfig
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbKey
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FilmsPagingSourceImpl constructor(
    private val api: TmdbApi,
    private val mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
    private val preference: PreferenceProvider
) : RxPagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Film>> {
        val nextPageNumber = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize
        Log.d("TAG", "nextPageNumber: $nextPageNumber ")
        Log.d("TAG", "pageSize: $pageSize ")
        return api.getFilms(
            preference.restoreCategorySetting(),
            TmdbKey.API_KEY_V3,
            TmdbConfig.LANGUAGE_RU,
            nextPageNumber
        )
            .subscribeOn(Schedulers.io()).map { filmsResponse ->
                mapper.mapOfResponse(filmsResponse, nextPageNumber)
            }.onErrorReturn {
                Log.d("TAG", "load: ${it.localizedMessage}")
                LoadResult.Error(it)
            }
    }

}