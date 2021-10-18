package com.w4ereT1ckRtB1tch.moviefan.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details.FilmDetailsViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites.FavoritesViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home.HomeViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections.SelectionViewModel
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @[IntoMap ViewModelKey(HomeViewModel::class)]
    abstract fun homeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(SelectionViewModel::class)]
    abstract fun selectionsViewModel(selectionViewModel: SelectionViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(FavoritesViewModel::class)]
    abstract fun favoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(FilmDetailsViewModel::class)]
    abstract fun filmDetailsViewModel(filmDetailsViewModel: FilmDetailsViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(SettingsViewModel::class)]
    abstract fun settingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}