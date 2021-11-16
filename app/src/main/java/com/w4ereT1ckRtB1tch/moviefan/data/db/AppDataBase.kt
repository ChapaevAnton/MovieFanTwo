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

    companion object {
        const val NAME = "TMDB.db"


        object Films {
            const val NAME = "films"

            object Columns {
                const val FILM_ID = "film_id"
                const val TITLE = "title"
                const val POSTER = "poster"
                const val BACKDROP = "backdrop"
                const val DESCRIPTION = "description"
                const val RATING = "rating"
                const val YEAR = "year"
                const val IS_FAVORITES = "is_favorites"
            }
        }

        object RemoteKeys {
            const val NAME = "film_remote_keys"

            object Columns {
                const val FILM_ID = "film_id"
                const val PREV_KEY = "prev_key"
                const val NEXT_KEY = "next_key"
            }
        }
    }
}