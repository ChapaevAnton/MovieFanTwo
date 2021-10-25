package com.w4ereT1ckRtB1tch.moviefan.data.db.rx

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.w4ereT1ckRtB1tch.moviefan.domain.model.FilmRemoteKeys

@Dao
interface FilmRemoteKeysRxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<FilmRemoteKeys>)

    @Query("SELECT * FROM film_remote_keys WHERE filmId = :filmId")
    fun remoteKeyByFilmId(filmId: Int): FilmRemoteKeys?

    @Query("DELETE FROM film_remote_keys")
    fun clearRemoteKeys()

}