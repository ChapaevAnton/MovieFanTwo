package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.repository.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataBase: FilmsRepository) : ViewModel() {

    private val popularFilms: MutableLiveData<PagingData<Film>> = MutableLiveData()
    fun getPopularFilms(): LiveData<PagingData<Film>> = popularFilms

    private val upcomingFilms: MutableLiveData<PagingData<Film>> = MutableLiveData()
    fun getUpcomingFilms(): LiveData<PagingData<Film>> = upcomingFilms

    private val compositeDisposable = CompositeDisposable()

    init {
        onLoadingUpcomingData()
        onLoadingPopularData()
    }

    private fun onLoadingUpcomingData() {
        compositeDisposable.add(dataBase
            .getUpcomingFilms()
            .cachedIn(viewModelScope)
            .subscribe {
                upcomingFilms.value = it
            })
    }

    private fun onLoadingPopularData() {
        compositeDisposable.add(dataBase
            .getPopularFilms()
            .cachedIn(viewModelScope)
            .subscribe {
                popularFilms.value = it
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun onRefreshPopularData() {
        onLoadingPopularData()
    }

}