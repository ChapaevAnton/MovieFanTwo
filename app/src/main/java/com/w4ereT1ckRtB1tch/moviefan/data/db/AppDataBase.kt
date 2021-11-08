package com.w4ereT1ckRtB1tch.moviefan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.w4ereT1ckRtB1tch.moviefan.data.db.dao.FilmRemoteKeysRxDao
import com.w4ereT1ckRtB1tch.moviefan.data.db.dao.FilmRxDao
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.model.FilmRemoteKeys

@Database(
    entities = [Film::class, FilmRemoteKeys::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun filmRxDao(): FilmRxDao

    abstract fun filmRemoteKeysRxDao(): FilmRemoteKeysRxDao

}