package com.w4ereT1ckRtB1tch.moviefan.data.repository

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmPopularResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsPopularResponse
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import retrofit2.Call

class FilmsRepositoryImpl(
    private val api: TmdbApi,
    private val mapper: FilmsMapper<FilmPopularResponse, FilmsPopularResponse>
) : FilmsRepository {

    override fun getPopularFilms(page: Int): Call<List<Film>> {
        TODO("Not yet implemented")
    }

}