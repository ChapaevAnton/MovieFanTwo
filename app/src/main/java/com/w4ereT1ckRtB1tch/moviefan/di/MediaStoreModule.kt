package com.w4ereT1ckRtB1tch.moviefan.di

import com.w4ereT1ckRtB1tch.moviefan.data.mediastore.ImageMediaStore
import com.w4ereT1ckRtB1tch.moviefan.data.mediastore.ImageMediaStoreImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MediaStoreModule {

    @Binds
    @Singleton
    fun bindImageMediaStore(imageMediaStore: ImageMediaStoreImpl): ImageMediaStore
}