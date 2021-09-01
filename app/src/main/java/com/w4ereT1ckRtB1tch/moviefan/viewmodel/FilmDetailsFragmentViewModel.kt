package com.w4ereT1ckRtB1tch.moviefan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.domain.Film

class FilmDetailsFragmentViewModel : ViewModel() {

    private val film: MutableLiveData<Film> = MutableLiveData()

    fun getFilm(): LiveData<Film> = film

}