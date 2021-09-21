package com.w4ereT1ckRtB1tch.moviefan.domain.mapper

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

interface FilmsMapper <in FILM, in FILMS> {

    fun map(film:FILM): Film

    fun map(films: FILMS): List<Film>

}