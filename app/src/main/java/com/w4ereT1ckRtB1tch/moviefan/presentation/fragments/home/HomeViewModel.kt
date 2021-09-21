package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.DataBase
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

class HomeViewModel : ViewModel() {

    private var dataBase: DataBase = App.instance.dataBase

    private val films: MutableLiveData<List<Film>> = MutableLiveData()
    fun getFilms(): LiveData<List<Film>> = films

    private val recommendFilms: MutableLiveData<List<Film>> = MutableLiveData()
    fun getRecommendFilms(): LiveData<List<Film>> = recommendFilms

    init {
        val filmsValue = dataBase.getDataBase()
        films.postValue(filmsValue)
        val recommendValues = dataBase.getDataBase().take(6)
        this.recommendFilms.postValue(recommendValues)
    }
}