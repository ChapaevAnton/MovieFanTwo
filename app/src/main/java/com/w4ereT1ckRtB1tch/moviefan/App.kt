package com.w4ereT1ckRtB1tch.moviefan

import android.app.Application
import com.w4ereT1ckRtB1tch.moviefan.data.Repository
import com.w4ereT1ckRtB1tch.moviefan.domain.DataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var dataBase: DataBase
    private lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = Repository()
        dataBase = DataBase(repository)
    }

}