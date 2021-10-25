package com.w4ereT1ckRtB1tch.moviefan.domain.db

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

interface DataBaseMock {

    fun getDataBase(): List<Film>
}