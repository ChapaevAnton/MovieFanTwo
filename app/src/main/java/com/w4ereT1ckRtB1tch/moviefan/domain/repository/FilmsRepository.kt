package com.w4ereT1ckRtB1tch.moviefan.domain.repository

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import retrofit2.Call

interface FilmsRepository {

    fun getPopularFilms(): Call<List<Film>>

}