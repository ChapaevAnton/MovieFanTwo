package com.w4ereT1ckRtB1tch.moviefan.domain.repository

import androidx.paging.PagingData
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import io.reactivex.Flowable
import io.reactivex.Single

interface FilmsRepository {

    fun getPopularFilms(page: Int): Single<List<Film>>

    fun getUpcomingFilms(page: Int): Single<List<Film>>

    fun getListFilms(): Flowable<PagingData<Film>>

}