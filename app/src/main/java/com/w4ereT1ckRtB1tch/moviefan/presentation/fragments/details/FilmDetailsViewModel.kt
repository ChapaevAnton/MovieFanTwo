package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class FilmDetailsViewModel @Inject constructor(private val context: Context) :
    ViewModel() {

    private val film: MutableLiveData<Film> = MutableLiveData()
    private val isVisible: MutableLiveData<Boolean> = MutableLiveData()

    fun getFilm(): LiveData<Film> = film

    fun setFilm(filmValue: Film?) {
        film.value = filmValue
    }

    fun isVisible(): LiveData<Boolean> = isVisible

    fun onClickedDetails() {
        if (isVisible.value == null) isVisible.value = false
        val visible = isVisible.value?.let { !it }
        Log.d("TAG", "onClickedDetails: ok -> $visible")
        isVisible.value = visible
    }

    fun onClickedFavorites() {
        val filmValue = film.value?.let { it.copy(isFavorites = !it.isFavorites) }
        film.value = filmValue
        Log.d("TAG", "onClickedFavorites: ${film.value}")
    }

    fun onClickedShare() {
        film.value?.let {
            val message = context.getString(
                R.string.intent_message,
                it.title,
                it.description,
                it.year?.year ?: context.getString(R.string.not_announced),
                it.rating.toString()
            )
            val intent = Intent().apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
            context.startActivity(intent)
        }
    }
}