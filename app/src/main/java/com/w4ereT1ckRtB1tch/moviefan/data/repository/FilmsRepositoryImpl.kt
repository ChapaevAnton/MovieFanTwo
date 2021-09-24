package com.w4ereT1ckRtB1tch.moviefan.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbConfig
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbKey
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val api: TmdbApi,
    private val mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
    private val source: FilmsPagingSourceImpl
) : FilmsRepository {

    override fun getPopularFilms(page: Int): Single<List<Film>> {
        return api.getPopularFilms(TmdbKey.API_KEY_V3, TmdbConfig.LANGUAGE_RU, page)
            .map { mapper.map(it) }
    }

    @ExperimentalCoroutinesApi
    override fun getUpcomingFilms(): Flowable<PagingData<Film>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 1,
            maxSize = 80
        )
        return Pager(config = config, pagingSourceFactory = { source }).flowable
    }

}