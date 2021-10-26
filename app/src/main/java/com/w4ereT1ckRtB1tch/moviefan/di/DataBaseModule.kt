package com.w4ereT1ckRtB1tch.moviefan.di

import android.app.Application
import androidx.room.Room
import com.w4ereT1ckRtB1tch.moviefan.data.db.FilmDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideFilmDataBase(application: Application): FilmDataBase =
        Room.databaseBuilder(
            application.applicationContext,
            FilmDataBase::class.java,
            "TMDB.db"
        ).build()

}