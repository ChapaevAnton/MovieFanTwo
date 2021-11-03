package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.db.DataBaseImplMock
import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBaseMock
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface DataBaseModuleMock {

    @Binds
    @Reusable
    fun bindDataBaseMock(dataBaseImplMock: DataBaseImplMock): DataBaseMock

}