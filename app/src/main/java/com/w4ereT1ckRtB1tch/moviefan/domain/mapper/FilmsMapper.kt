package com.w4ereT1ckRtB1tch.moviefan.domain.mapper

import androidx.paging.PagingSource.LoadResult
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Films

interface FilmsMapper<in FILM, in FILMS> {

    fun mapOfResponse(film: FILM): Film

    fun mapOfResponse(films: List<FILM>): List<Film>

    fun mapOfResponse(films: FILMS): Films

    fun mapOfResponse(films: FILMS, page: Int): LoadResult<Int, Film>

}