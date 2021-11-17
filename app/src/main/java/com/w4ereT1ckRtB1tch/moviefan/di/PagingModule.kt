package com.w4ereT1ckRtB1tch.moviefan.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.rxjava2.RxPagingSource
import androidx.paging.rxjava2.RxRemoteMediator
import com.w4ereT1ckRtB1tch.moviefan.data.db.AppDataBase
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmResponse
import com.w4ereT1ckRtB1tch.moviefan.data.dto.FilmsResponse
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsRemoteMediatorImpl
import com.w4ereT1ckRtB1tch.moviefan.data.source.TmdbApi
import com.w4ereT1ckRtB1tch.moviefan.domain.mapper.FilmsMapper
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Qualifier

@Module
object PagingModule {

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
    @ExperimentalPagingApi
    fun provideHomeBottomPanelRemoteMediator(
        api: TmdbApi,
        mapper: @JvmSuppressWildcards FilmsMapper<FilmResponse, FilmsResponse>,
        dataBase: AppDataBase,
        @HomeBottomSettings
        preference: PreferenceProvider
    ): RxRemoteMediator<Int, Film> = FilmsRemoteMediatorImpl(api, mapper, dataBase, preference)
}

@Qualifier
annotation class PagingPopular

@Qualifier
annotation class PagingUpcoming