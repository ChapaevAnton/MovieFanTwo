package com.w4ereT1ckRtB1tch.moviefan.data.mapper

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbConfig
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FilmsMapperImpl @Inject constructor() :
    FilmsMapper<@JvmSuppressWildcards FilmResponse, @JvmSuppressWildcards FilmsResponse> {

    override fun map(film: FilmResponse): Film {
        return with(film) {
            Film(
                id = id,
                title = title,
                poster = "${TmdbConfig.IMAGE_URL}${TmdbConfig.IMAGE_POSTER_SIZE_50}$posterPath",
                backdrop = "${TmdbConfig.IMAGE_URL}${TmdbConfig.IMAGE_BACKDROP_SIZE}$backdropPath",
                description = overview,
                rating = voteAverage,
                year = releaseDate?.let { LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE) },
                isFavorites = false
            )
        }
    }

    override fun map(films: FilmsResponse): List<Film> {
        return films.results.map { map(it) }.toList()
    }

}