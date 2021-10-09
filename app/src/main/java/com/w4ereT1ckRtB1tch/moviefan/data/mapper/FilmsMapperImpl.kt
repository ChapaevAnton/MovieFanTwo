package com.w4ereT1ckRtB1tch.moviefan.data.mapper

import androidx.paging.PagingSource
import com.w4ereT1ckRtB1tch.moviefan.App
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

    init {
        App.instance.appComponent.inject(this)
    }

    override fun mapOfResponse(film: FilmResponse): Film {
        return with(film) {
            Film(
                id = id,
                title = title,
                poster = "${TmdbConfig.IMAGE_URL}${TmdbConfig.IMAGE_POSTER_SIZE_50}$posterPath",
                backdrop = "${TmdbConfig.IMAGE_URL}${TmdbConfig.IMAGE_BACKDROP_SIZE}$backdropPath",
                description = overview,
                rating = voteAverage,
                year = releaseDate.toLocalDate(),
                isFavorites = false
            )
        }
    }

    override fun mapOfResponse(films: FilmsResponse): List<Film> {
        return films.results.map { mapOfResponse(it) }.toList()
    }

    override fun mapOfResponse(
        films: FilmsResponse,
        page: Int
    ): PagingSource.LoadResult<Int, Film> {
        return PagingSource.LoadResult.Page(
            data = mapOfResponse(films),
            prevKey = if (page == 1) null else page.minus(1),
            nextKey = if (page == films.totalPages) null else page.plus(1)
        )
    }

    private fun String?.toLocalDate(): LocalDate? {
        return if (this.isNullOrEmpty()) null else LocalDate.parse(
            this,
            DateTimeFormatter.ISO_LOCAL_DATE
        )
    }

}