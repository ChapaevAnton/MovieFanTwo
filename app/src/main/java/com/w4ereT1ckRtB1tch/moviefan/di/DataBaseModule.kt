package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.db.DataBaseImplMock
import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBaseMock
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class DataBaseModule {

    @Binds
    @Reusable
    abstract fun bindDataBase(dataBaseImplMock: DataBaseImplMock): DataBaseMock
}