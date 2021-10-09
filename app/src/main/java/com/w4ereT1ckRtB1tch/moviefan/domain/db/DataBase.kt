package com.w4ereT1ckRtB1tch.moviefan.domain.db

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

interface DataBase {

    fun getDataBase(): List<Film>
}