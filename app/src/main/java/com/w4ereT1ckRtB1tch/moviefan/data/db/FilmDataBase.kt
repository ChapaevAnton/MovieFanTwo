package com.w4ereT1ckRtB1tch.moviefan.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.w4ereT1ckRtB1tch.moviefan.data.db.rx.FilmRemoteKeysRxDao
import com.w4ereT1ckRtB1tch.moviefan.data.db.rx.FilmRxDao
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.domain.model.FilmRemoteKeys

@Database(
    entities = [Film::class, FilmRemoteKeys::class],
    version = 1,
    exportSchema = false
)


@TypeConverters(Converters::class)
abstract class FilmDataBase : RoomDatabase() {

    abstract fun filmRxDao(): FilmRxDao

    abstract fun filmRemoteKeysRxDao(): FilmRemoteKeysRxDao

}