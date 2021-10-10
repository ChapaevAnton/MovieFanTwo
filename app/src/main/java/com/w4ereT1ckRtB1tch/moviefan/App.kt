package com.w4ereT1ckRtB1tch.moviefan

import android.app.Application
import com.w4ereT1ckRtB1tch.moviefan.di.AppComponent
import com.w4ereT1ckRtB1tch.moviefan.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().application(this).build()
    }

}