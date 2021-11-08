package com.w4ereT1ckRtB1tch.moviefan.data.db

class FilmDataBaseScheme {

    object DataBase {
        const val NAME = "TMDB.db"

        object FilmTable {
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

        object FilmRemoteTable {
            const val NAME = "film_remote_keys"

            object Columns {
                const val FILM_ID = "film_id"
                const val PREV_KEY = "prev_key"
                const val NEXT_KEY = "next_key"
            }
        }
    }

}