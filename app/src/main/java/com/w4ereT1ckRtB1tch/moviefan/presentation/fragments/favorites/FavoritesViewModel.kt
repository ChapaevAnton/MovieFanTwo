package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBaseMock
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import javax.inject.Inject


class FavoritesViewModel @Inject constructor(private val dataBase: DataBaseMock) : ViewModel() {

    private val films: MutableLiveData<List<Film>> = MutableLiveData()

    fun getFavoritesFilms(): LiveData<List<Film>> = films

    init {
        val filmsValue = dataBase.getDataBase().filter { it.isFavorites }
        films.postValue(filmsValue)
    }

    fun setFavoritesFilms() {
        val filmsValue = dataBase.getDataBase().filter { it.isFavorites }
        films.postValue(filmsValue)
    }
}