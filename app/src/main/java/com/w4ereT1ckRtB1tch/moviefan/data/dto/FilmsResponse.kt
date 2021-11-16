package com.w4ereT1ckRtB1tch.moviefan.data.dto

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @field:SerializedName("dates") val dates: Dates = Dates("", ""),
    @field:SerializedName("page") val page: Int,
    @field:SerializedName("results") val results: List<FilmResponse>,
    @field:SerializedName("total_pages") val totalPages: Int,
    @field:SerializedName("total_results") val totalResults: Int
)