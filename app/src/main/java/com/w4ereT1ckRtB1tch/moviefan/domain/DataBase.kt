package com.w4ereT1ckRtB1tch.moviefan.domain

import com.w4ereT1ckRtB1tch.moviefan.data.Repository

class DataBase(private val repository: Repository) {
    fun getDataBase(): List<Film> = repository.filmDataBase
}