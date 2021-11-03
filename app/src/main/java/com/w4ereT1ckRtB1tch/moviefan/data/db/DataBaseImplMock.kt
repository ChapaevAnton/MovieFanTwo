package com.w4ereT1ckRtB1tch.moviefan.data.db

import com.w4ereT1ckRtB1tch.moviefan.domain.db.DataBaseMock
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import javax.inject.Inject

class DataBaseImplMock @Inject constructor(private val repositoryMock: RepositoryMock) :
    DataBaseMock {
    override fun getDataBase(): List<Film> = repositoryMock.filmDataBase

}