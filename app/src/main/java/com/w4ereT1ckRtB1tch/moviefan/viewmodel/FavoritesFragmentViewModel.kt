package com.w4ereT1ckRtB1tch.moviefan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.Film

class FavoritesFragmentViewModel : ViewModel() {

    private val dataBase = App.instance.dataBase

    private val films: MutableLiveData<List<Film>> = MutableLiveData()
    val getFavoritesFilms: LiveData<List<Film>> get() = films

    init {
        val value = dataBase.getDataBase().filter { it.favorites }
        films.postValue(value)
    }

    fun updateFavoritesFilms() {
        val value = dataBase.getDataBase().filter { it.favorites }
        films.postValue(value)
    }
}