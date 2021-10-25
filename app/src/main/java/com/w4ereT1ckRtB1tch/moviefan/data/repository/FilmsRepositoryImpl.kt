package com.w4ereT1ckRtB1tch.moviefan.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.RxPagingSource
import androidx.paging.rxjava2.flowable
import com.w4ereT1ckRtB1tch.moviefan.data.db.FilmDataBase
import com.w4ereT1ckRtB1tch.moviefan.di.PagingPopular
import com.w4ereT1ckRtB1tch.moviefan.di.PagingUpcoming
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    @PagingUpcoming
    private val upcomingSource: RxPagingSource<Int, Film>,
    @PagingPopular
    private val popularSource: RxPagingSource<Int, Film>,
    private val remoteMediator: FilmsRemoteMediator,
    private val dataBase: FilmDataBase
) : FilmsRepository {

    private val configPopular = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false,
        prefetchDistance = 2,
        maxSize = 500
    )

    private val configUpcoming = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false,
        prefetchDistance = 2,
        maxSize = 100
    )

//    @ExperimentalCoroutinesApi
//    override fun getPopularFilms(): Flowable<PagingData<Film>> {
//        return Pager(
//            config = configPopular,
//            pagingSourceFactory = { popularSource }
//        ).flowable
//    }

    // FIXME: 25.10.2021 not working pagingSourceFactory = { dataBase.filmRxDao().selectAll() }
    @ExperimentalPagingApi
    @ExperimentalCoroutinesApi
    override fun getPopularFilms(): Flowable<PagingData<Film>> {
        return Pager(
            config = configPopular,
            remoteMediator = remoteMediator,
            pagingSourceFactory = { popularSource }
        ).flowable
    }

    @ExperimentalCoroutinesApi
    override fun getUpcomingFilms(): Flowable<PagingData<Film>> {
        return Pager(
            config = configUpcoming,
            pagingSourceFactory = { upcomingSource }
        ).flowable
    }

}