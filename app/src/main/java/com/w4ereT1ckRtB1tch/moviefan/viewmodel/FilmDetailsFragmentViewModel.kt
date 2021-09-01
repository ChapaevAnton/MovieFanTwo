package com.w4ereT1ckRtB1tch.moviefan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.Film

class FilmDetailsFragmentViewModel : ViewModel() {

    private val film: MutableLiveData<Film> = MutableLiveData()
    private val isVisible: MutableLiveData<Boolean> = MutableLiveData()

    fun getFilm(): LiveData<Film> = film

    fun setFilm(value: Film) {
        film.postValue(value)
    }

    fun isVisible(): LiveData<Boolean> = isVisible

    fun onClickedDetails() {
        if (isVisible.value == null) isVisible.value = false
        var value = isVisible.value!!
        value = !value
        Log.d("TAG", "onClickedDetails: ok -> $value")
        isVisible.postValue(value)
    }

    fun onClickedFavorites() {
        val value = film.value!!
        value.isFavorites = !value.isFavorites
        film.postValue(value)
        Log.d("TAG", "DataBase: ${App.instance.dataBase.getDataBase()}")
    }

    fun onClickedShare() {
        Log.d("TAG", "onClickedShare: ok")
    }
}