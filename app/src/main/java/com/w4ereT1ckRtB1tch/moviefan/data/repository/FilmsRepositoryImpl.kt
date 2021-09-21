package com.w4ereT1ckRtB1tch.moviefan.data.repository

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import retrofit2.Call

class FilmsRepositoryImpl : FilmsRepository {

    override fun getPopularFilms(): Call<List<Film>> {
        TODO("Not yet implemented")
    }

}