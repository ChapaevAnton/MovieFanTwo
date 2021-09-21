package com.w4ereT1ckRtB1tch.moviefan.data.repository

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsPopularResponse
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbConfig
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbKey
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import io.reactivex.Single
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val api: TmdbApi,
    private val mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsPopularResponse>
) : FilmsRepository {

    override fun getPopularFilms(page: Int): Single<List<Film>> {
        return api.getPopularFilms(TmdbKey.API_KEY_V3, TmdbConfig.LANGUAGE, page)
            .map { mapper.map(it) }
    }

}