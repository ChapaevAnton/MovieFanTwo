package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.preferences.HomeBottomPanelSettings
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.HomeTopPanelSettings
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
interface PreferenceProviderModule {

    @Binds
    @Singleton
    @HomeTopSettings
    fun bindHomeTopPanelSettings(
        homeTopPanelSettings: HomeTopPanelSettings
    ): PreferenceProvider

    @Binds
    @Singleton
    @HomeBottomSettings
    fun bindHomeBottomPanelSettings(
        homeBottomPanelSettings: HomeBottomPanelSettings
    ): PreferenceProvider
}

@Qualifier
annotation class HomeTopSettings

@Qualifier
annotation class HomeBottomSettings