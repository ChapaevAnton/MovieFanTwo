package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.db.DataBaseImpl
import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBase
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class DataBaseModule {

    @Binds
    @Reusable
    abstract fun bindDataBase(dataBaseImpl: DataBaseImpl): DataBase
}