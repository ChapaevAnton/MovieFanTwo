package com.w4ereT1ckRtB1tch.moviefan.data.db

import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBase
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import javax.inject.Inject

class DataBaseImpl @Inject constructor() : DataBase {
    private val repository = Repository()
    override fun getDataBase(): List<Film> = repository.filmDataBase
}