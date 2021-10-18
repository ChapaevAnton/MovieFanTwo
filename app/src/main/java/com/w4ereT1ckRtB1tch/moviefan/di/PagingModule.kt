package com.w4ereT1ckRtB1tch.moviefan.di

import androidx.paging.rxjava2.RxPagingSource
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsMiniPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsPagingSourceImpl
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
    abstract fun bindsFilmsPaging(filmsPagingSourceImpl: FilmsPagingSourceImpl): RxPagingSource<Int, Film>

    @Binds
    @Reusable
    @PagingUpcoming
    abstract fun bindsFilmsMiniPaging(filmsMiniPagingSourceImpl: FilmsMiniPagingSourceImpl): RxPagingSource<Int, Film>

}

@Qualifier
annotation class PagingPopular

@Qualifier
annotation class PagingUpcoming