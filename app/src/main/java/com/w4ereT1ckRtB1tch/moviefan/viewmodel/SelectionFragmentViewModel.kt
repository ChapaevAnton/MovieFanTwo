package com.w4ereT1ckRtB1tch.moviefan.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.domain.Film

class SelectionFragmentViewModel : ViewModel() {

    private var dataBase = App.instance.dataBase

    private val films: MutableLiveData<List<Film>> = MutableLiveData()
    val getFilms: LiveData<List<Film>> get() = films
    private var onQueryTextListener: SearchView.OnQueryTextListener? = null

    init {
        val values = dataBase.getDataBase()
        films.postValue(values)
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
                            return true
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
        val values = dataBase.getDataBase()
        films.postValue(values)
    }

    private fun setFilter(filter: String) {
        val values = dataBase.getDataBase()
            .filter { film -> film.title.lowercase().contains(filter.lowercase()) }
        films.postValue(values)
    }

}