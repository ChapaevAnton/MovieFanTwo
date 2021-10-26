package com.w4ereT1ckRtB1tch.moviefan.di

import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Qualifier

@Module
class PagingModule {

    @Provides
    @Reusable
    @PagingUpcoming
    fun provideHomeTopPanelPagingSource(
        api: TmdbApi,
        mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
        @HomeTopSettings
        preference: PreferenceProvider
    ): RxPagingSource<Int, Film> = FilmsPagingSourceImpl(api, mapper, preference)

    @Provides
    @Reusable
    @PagingPopular
    fun provideHomeBottomPanelPagingSource(
        api: TmdbApi,
        mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
        @HomeBottomSettings
        preference: PreferenceProvider
    ): RxPagingSource<Int, Film> = FilmsPagingSourceImpl(api, mapper, preference)

}

@Qualifier
annotation class PagingPopular

@Qualifier
annotation class PagingUpcoming