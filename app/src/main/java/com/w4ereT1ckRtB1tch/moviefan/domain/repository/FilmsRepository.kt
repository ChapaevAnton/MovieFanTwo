package com.w4ereT1ckRtB1tch.moviefan.domain.repository

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import io.reactivex.Single

interface FilmsRepository {

    fun getPopularFilms(page: Int): Single<List<Film>>

    fun getUpcomingFilms(page: Int): Single<List<Film>>
}