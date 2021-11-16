package com.w4ereT1ckRtB1tch.moviefan

import com.w4ereT1ckRtB1tch.moviefan.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().context(this.applicationContext).build()
    }

}