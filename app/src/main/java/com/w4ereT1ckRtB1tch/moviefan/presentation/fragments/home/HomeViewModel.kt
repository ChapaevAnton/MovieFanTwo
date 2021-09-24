package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataBase: FilmsRepository) : ViewModel() {

    private val films: MutableLiveData<List<Film>> = MutableLiveData()
    fun getFilms(): LiveData<List<Film>> = films

    private val upcomingFilms: MutableLiveData<PagingData<Film>> = MutableLiveData()
    fun getUpcomingFilms(): LiveData<PagingData<Film>> = upcomingFilms

    private val compositeDisposable = CompositeDisposable()

    init {
        loadingData()
    }

    @SuppressLint("CheckResult")
    private fun getPopular(page: Int) {
        dataBase.getPopularFilms(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                films.value = list
            }, { error ->
                Log.d("TAG", error.toString())
            })
    }

    @ExperimentalCoroutinesApi
    private fun loadingData() {
        compositeDisposable.add(dataBase
            .getUpcomingFilms()
            .cachedIn(viewModelScope)
            .subscribe {
                upcomingFilms.value = it
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}