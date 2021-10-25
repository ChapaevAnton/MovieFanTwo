package com.w4ereT1ckRtB1tch.moviefan.data.db

import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBaseMock
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import javax.inject.Inject

class DataBaseImplMock @Inject constructor() : DataBaseMock {
    private val repository = RepositoryMock()
    override fun getDataBase(): List<Film> = repository.filmDataBase
}