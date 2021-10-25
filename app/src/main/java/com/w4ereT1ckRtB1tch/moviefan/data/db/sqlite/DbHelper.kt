package com.w4ereT1ckRtB1tch.moviefan.data.db.sqlite

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.w4ereT1ckRtB1tch.moviefan.data.db.sqlite.DbScheme.FilmTable

class DbHelper(application: Application) : SQLiteOpenHelper(
    application.applicationContext,
    DATABASE_NAME, null, DATABASE_VERSION
) {

    companion object {
        const val DATABASE_NAME = "films.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """CREATE TABLE ${FilmTable.NAME} (
            |_id INTEGER PRIMARY KEY AUTOINCREMENT,
            |${FilmTable.Columns.ID} INTEGER UNIQUE NOT NULL,
            |${FilmTable.Columns.TITLE} TEXT NOT NULL,
            |${FilmTable.Columns.POSTER} TEXT,
            |${FilmTable.Columns.BACKDROP} TEXT,
            |${FilmTable.Columns.DESCRIPTION} TEXT NOT NULL,
            |${FilmTable.Columns.RATING} REAL NOT NULL,
            |${FilmTable.Columns.YEAR} INTEGER,
            |${FilmTable.Columns.IS_FAVORITES} INTEGER DEFAULT 0
            |)""".trimMargin()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}