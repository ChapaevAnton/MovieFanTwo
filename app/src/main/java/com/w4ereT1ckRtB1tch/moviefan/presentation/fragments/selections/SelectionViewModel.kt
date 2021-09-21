package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.Film

class SelectionViewModel : ViewModel() {

    private var dataBase = App.instance.dataBase

    private val films: MutableLiveData<List<Film>> = MutableLiveData()

    fun getFilms(): LiveData<List<Film>> = films

    private var onQueryTextListener: SearchView.OnQueryTextListener? = null

    init {
        val filmsValue = dataBase.getDataBase()
        films.postValue(filmsValue)
    }

    fun onQueryTextListener(): SearchView.OnQueryTextListener? {
        if (onQueryTextListener == null) {

            onQueryTextListener = object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (it.isEmpty()) {
                            setFilter()
                            return false
                        }
                        setFilter(it)
                    }
                    return true
                }
            }
        }
        return onQueryTextListener
    }

    private fun setFilter() {
        val filmsValue = dataBase.getDataBase()
        films.postValue(filmsValue)
    }

    private fun setFilter(filter: String) {
        val filmsValue = dataBase.getDataBase()
            .filter { film -> film.title.lowercase().contains(filter.lowercase()) }
        films.postValue(filmsValue)
    }

}