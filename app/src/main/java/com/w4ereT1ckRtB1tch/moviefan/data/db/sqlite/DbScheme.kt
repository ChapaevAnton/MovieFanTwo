package com.w4ereT1ckRtB1tch.moviefan.data.db.sqlite

class DbScheme {

    object FilmTable {
        const val NAME = "films_table"

        object Columns {
            const val ID = "id"
            const val TITLE = "title"
            const val POSTER = "poster"
            const val BACKDROP = "backdrop"
            const val DESCRIPTION = "description"
            const val RATING = "rating"
            const val YEAR = "year"
            const val IS_FAVORITES = "is_favorites"
        }
    }
}