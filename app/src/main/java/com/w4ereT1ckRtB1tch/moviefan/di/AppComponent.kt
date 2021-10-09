package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.db.DataBaseImpl
import com.w4ereT1ckRtB1tch.moviefan.data.mapper.FilmsMapperImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.FilmsRepositoryImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsPopularPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.data.repository.paging.FilmsUpcomingPagingSourceImpl
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelModule
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites.FavoritesFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites.FavoritesViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home.HomeFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home.HomeViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections.SelectionViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections.SelectionsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataBaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(homeViewModel: HomeViewModel)
    fun inject(selectionViewModel: SelectionViewModel)
    fun inject(favoritesViewModel: FavoritesViewModel)
    fun inject(filmsRepositoryImpl: FilmsRepositoryImpl)
    fun inject(filmsMapperImpl: FilmsMapperImpl)
    fun inject(filmsPopularPagingSourceImpl: FilmsPopularPagingSourceImpl)
    fun inject(filmsUpcomingPagingSourceImpl: FilmsUpcomingPagingSourceImpl)
    fun inject(homeFragment: HomeFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(selectionsFragment: SelectionsFragment)
    fun inject(dataBaseImpl: DataBaseImpl)

}