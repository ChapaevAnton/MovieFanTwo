package com.w4ereT1ckRtB1tch.moviefan.di

import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    @PopularFilm
    fun providePopular(): String = "popular"

    @Provides
    @Singleton
    @UpcomingFilm
    fun provideUpcoming(): String = "upcoming"

    @Provides
    @Reusable
    @PagingPopular
    fun provideFilmsPopular(
        api: TmdbApi,
        mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
        @PopularFilm
        category: String
    ): RxPagingSource<Int, Film> = FilmsPagingSourceImpl(api, mapper, category)

    @Provides
    @Reusable
    @PagingUpcoming
    fun provideFilmsUpcoming(
        api: TmdbApi,
        mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
        @UpcomingFilm
        category: String
    ): RxPagingSource<Int, Film> = FilmsPagingSourceImpl(api, mapper, category)

}

@Qualifier
annotation class PagingPopular

@Qualifier
annotation class PagingUpcoming

@Qualifier
annotation class PopularFilm

@Qualifier
annotation class UpcomingFilm