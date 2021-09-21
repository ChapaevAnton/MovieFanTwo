package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import dagger.hilt.android.lifecycle.HiltViewModel


class FilmDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val film: MutableLiveData<Film> = MutableLiveData()
    private val isVisible: MutableLiveData<Boolean> = MutableLiveData()

    fun getFilm(): LiveData<Film> = film

    fun setFilm(value: Film) {
        film.postValue(value)
    }

    fun isVisible(): LiveData<Boolean> = isVisible

    fun onClickedDetails() {
        if (isVisible.value == null) isVisible.value = false
        var filmValue = isVisible.value!!
        filmValue = !filmValue
        Log.d("TAG", "onClickedDetails: ok -> $filmValue")
        isVisible.postValue(filmValue)
    }

    fun onClickedFavorites() {
        val filmValue = film.value!!
        filmValue.isFavorites = !filmValue.isFavorites
        film.postValue(filmValue)
        //Log.d("TAG", "DataBase: ${App.instance.dataBase.getDataBase()}")
    }

    fun onClickedShare() {
        val filmValue = film.value!!
        val message = """Обязательно посмотри этот фильм:
                         |Название: "${filmValue.title}"
                         |Описание: ${filmValue.description}
                         |Год выпуска: ${filmValue.year.year}
                         |Рейтинг: ${filmValue.rating}""".trimMargin()
        val intent = Intent().apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        getApplication<App>().applicationContext.startActivity(intent)
        Log.d("TAG", "onClickedShare: ok")
    }
}