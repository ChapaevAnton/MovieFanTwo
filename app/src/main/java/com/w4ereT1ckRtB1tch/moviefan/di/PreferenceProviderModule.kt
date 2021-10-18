package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.preferences.HomeBottomPanelSettings
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.HomeTopPanelSettings
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProvider
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
abstract class PreferenceProviderModule {

    @Binds
    @Singleton
    @HomeTopSettings
    abstract fun provideHomeTopPanelSettings(
        homeTopPanelSettings: HomeTopPanelSettings
    ): PreferenceProvider

    @Binds
    @Singleton
    @HomeBottomSettings
    abstract fun provideHomeBottomPanelSettings(
        homeBottomPanelSettings: HomeBottomPanelSettings
    ): PreferenceProvider
}

@Qualifier
annotation class HomeTopSettings

@Qualifier
annotation class HomeBottomSettings
