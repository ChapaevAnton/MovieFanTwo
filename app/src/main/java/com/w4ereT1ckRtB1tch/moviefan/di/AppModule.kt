package com.w4ereT1ckRtB1tch.moviefan.di

import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.mapper.FilmsMapperImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.FilmsPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.FilmsRepositoryImpl
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Reusable
    abstract fun bindFilmsMapper(
        filmsMapper: FilmsMapperImpl
    ): @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>

    @Binds
    @Reusable
    abstract fun bindFilmsRepository(filmsRepository: FilmsRepositoryImpl): FilmsRepository

    @Binds
    @Reusable
    abstract fun bindFilmsPagingSource(
        filmsPagingSource: FilmsPagingSourceImpl
    ): @JvmSuppressWildcards RxPagingSource<Int, Film>

}