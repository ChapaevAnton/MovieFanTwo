package com.w4ereT1ckRtB1tch.moviefan.domain.repository

import androidx.paging.PagingData
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import io.reactivex.Flowable

interface FilmsRepository {

    fun getPopularFilms(): Flowable<PagingData<Film>>

    fun getUpcomingFilms(): Flowable<PagingData<Film>>

}