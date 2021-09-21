package com.w4ereT1ckRtB1tch.moviefan.data.mapper

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmPopularResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsPopularResponse
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FilmsMapperImpl : FilmsMapper<FilmPopularResponse, FilmsPopularResponse> {

    override fun map(film: FilmPopularResponse): Film {
        return with(film) {
            Film(
                title = title,
                poster =  posterPath,
                description = overview,
                rating = voteAverage,
                year = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_LOCAL_DATE),
                isFavorites = false
            )
        }
    }

    override fun map(films: FilmsPopularResponse): List<Film> {
        return films.results.map { map(it) }.toList()
    }
}