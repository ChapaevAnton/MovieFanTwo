package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.mapper.FilmsMapperImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.FilmsRepositoryImpl
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface AppModule {

    @Binds
    @Reusable
    fun bindFilmsMapper(
        filmsMapper: FilmsMapperImpl
    ): @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>

    @Binds
    @Reusable
    fun bindFilmsRepository(filmsRepository: FilmsRepositoryImpl): FilmsRepository

}