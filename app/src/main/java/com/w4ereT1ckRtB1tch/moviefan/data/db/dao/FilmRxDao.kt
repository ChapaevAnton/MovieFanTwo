package com.w4ereT1ckRtB1tch.moviefan.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

@Dao
interface FilmRxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(films: List<Film>)

    @Query("SELECT * FROM films ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, Film>

    @Query("DELETE FROM films")
    fun clearFilms()

}