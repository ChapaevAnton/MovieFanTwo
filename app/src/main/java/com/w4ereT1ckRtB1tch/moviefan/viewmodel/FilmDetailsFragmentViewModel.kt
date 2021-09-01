package com.w4ereT1ckRtB1tch.moviefan.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.Film
import com.w4ereT1ckRtB1tch.moviefan.utils.Event

class FilmDetailsFragmentViewModel : ViewModel() {

    private val film: MutableLiveData<Film> = MutableLiveData()
    private val details: MutableLiveData<Event> = MutableLiveData()

    fun getFilm(): LiveData<Film> = film

    fun setFilm(value: Film) {
        film.postValue(value)
    }

    fun getDetails(): LiveData<Event> = details

    fun onClickedDetails() {
        details.postValue(Event())
    }

    fun onClickedFavorites() {
        val value = film.value
        value?.let { it.isFavorites = !it.isFavorites }
        film.postValue(value)
        Log.d("TAG", "DataBase: ${App.instance.dataBase.getDataBase()}")
    }
}