package com.w4ereT1ckRtB1tch.moviefan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.DataBase
import com.w4ereT1ckRtB1tch.moviefan.domain.Film

class HomeFragmentViewModel : ViewModel() {

    private var dataBase: DataBase = App.instance.dataBase

    private val films: MutableLiveData<List<Film>> = MutableLiveData()
    val getFilms: LiveData<List<Film>> get() = films

    private val recommendFilms: MutableLiveData<List<Film>> = MutableLiveData()
    val getRecommendFilms: LiveData<List<Film>> get() = recommendFilms

    init {
        val values = dataBase.getDataBase()
        films.postValue(values)
        val recommendValues = dataBase.getDataBase().take(6)
        this.recommendFilms.postValue(recommendValues)
    }
}