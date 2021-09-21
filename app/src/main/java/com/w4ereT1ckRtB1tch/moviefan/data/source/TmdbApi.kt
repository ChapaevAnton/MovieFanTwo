package com.w4ereT1ckRtB1tch.moviefan.data.source

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsPopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("3/movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<FilmsPopularResponse>
}