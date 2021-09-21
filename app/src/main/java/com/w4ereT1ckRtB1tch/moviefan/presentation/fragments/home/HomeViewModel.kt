package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataBase: FilmsRepository) : ViewModel() {

    private val films: MutableLiveData<List<Film>> = MutableLiveData()
    fun getFilms(): LiveData<List<Film>> = films

    private val recommendFilms: MutableLiveData<List<Film>> = MutableLiveData()
    fun getRecommendFilms(): LiveData<List<Film>> = recommendFilms

    init {
        loadDataBase()

//        val recommendValues = dataBase.getDataBase().take(6)
//        this.recommendFilms.postValue(recommendValues)
    }

    @SuppressLint("CheckResult")
    private fun loadDataBase() {
        dataBase.getPopularFilms(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                films.value = it
            }, { error ->
                Log.d("TAG", error.toString())
            })
    }

}