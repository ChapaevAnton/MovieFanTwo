package com.w4ereT1ckRtB1tch.moviefan.di

import android.app.Application
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providePreferenceProvider(application: Application): PreferenceProvider =
        PreferenceProvider(application)

}


