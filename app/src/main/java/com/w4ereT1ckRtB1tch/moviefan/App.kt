package com.w4ereT1ckRtB1tch.moviefan

import android.app.Application
import com.w4ereT1ckRtB1tch.moviefan.domain.DataBase
import com.w4ereT1ckRtB1tch.moviefan.data.Repository

class App : Application() {

    companion object{
        lateinit var instance:App
        private set
    }

    lateinit var repository: Repository
    lateinit var dataBase: DataBase

    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = Repository()
        dataBase = DataBase(repository)
    }
}