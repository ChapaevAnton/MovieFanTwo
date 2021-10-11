package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details.FilmDetailsFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites.FavoritesFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home.HomeFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections.SelectionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFilmDetailsFragment():FilmDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragment():FavoritesFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment():HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeSelectionsFragment():SelectionsFragment
}