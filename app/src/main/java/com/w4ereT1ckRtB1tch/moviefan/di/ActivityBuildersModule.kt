package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details.FilmDetailsFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites.FavoritesFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home.HomeFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections.SelectionsFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuildersModule {

    @ContributesAndroidInjector
    fun contributeFilmDetailsFragment(): FilmDetailsFragment

    @ContributesAndroidInjector
    fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    fun contributeSelectionsFragment(): SelectionsFragment

    @ContributesAndroidInjector
    fun contributesSettingsFragment(): SettingsFragment
}