package com.w4ereT1ckRtB1tch.moviefan.data.source

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("3/movie/{category}")
    fun getFilms(
        @Path("category") category: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<FilmsResponse>

}