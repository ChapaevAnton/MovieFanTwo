package com.w4ereT1ckRtB1tch.moviefan.di

import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.HomeBottomPanelPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.HomeTopPanelPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import dagger.Binds
import dagger.Module
import dagger.Reusable
import javax.inject.Qualifier

@Module
abstract class PagingModule {

    @Binds
    @Reusable
    @PagingPopular
    abstract fun bindHomeTopPanelPagingSource(
        homeTopPanelPagingSourceImpl: HomeTopPanelPagingSourceImpl
    ): RxPagingSource<Int, Film>

    @Binds
    @Reusable
    @PagingUpcoming
    abstract fun bindHomeBottomPanelPagingSource(
        homeBottomPanelPagingSourceImpl: HomeBottomPanelPagingSourceImpl
    ): RxPagingSource<Int, Film>

}

@Qualifier
annotation class PagingPopular

@Qualifier
annotation class PagingUpcoming