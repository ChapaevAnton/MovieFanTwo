package com.w4ereT1ckRtB1tch.moviefan.di

import android.content.Context
import androidx.room.Room
import com.w4ereT1ckRtB1tch.moviefan.data.db.AppDataBase
import com.w4ereT1ckRtB1tch.moviefan.data.db.RepositoryMock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideFilmDataBase(context: Context): AppDataBase =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            AppDataBase.NAME
        ).build()

    @Provides
    @Singleton
    fun provideRepositoryMock(): RepositoryMock = RepositoryMock()

}