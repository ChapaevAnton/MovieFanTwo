package com.w4ereT1ckRtB1tch.moviefan.data.dto

import com.google.gson.annotations.SerializedName

data class PopularFilmsResponse(
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("results") val filmsDto: List<FilmDto>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("total_results") val totalResults: Int
)