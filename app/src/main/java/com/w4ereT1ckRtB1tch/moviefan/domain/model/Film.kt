package com.w4ereT1ckRtB1tch.moviefan.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.w4ereT1ckRtB1tch.moviefan.data.db.FilmDataBaseScheme.DataBase.FilmRemoteTable
import com.w4ereT1ckRtB1tch.moviefan.data.db.FilmDataBaseScheme.DataBase.FilmTable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate


@Parcelize
data class Films(
    val page: Int = 0,
    val totalPage: Int = 0,
    val films: List<Film>,
) : Parcelable {

    @IgnoredOnParcel
    val endOfPage: Boolean = page == totalPage
}

@Parcelize
@Entity(tableName = FilmTable.NAME)
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = FilmTable.Columns.FILM_ID) val filmId: Int,
    @ColumnInfo(name = FilmTable.Columns.TITLE) val title: String,
    @ColumnInfo(name = FilmTable.Columns.POSTER) val poster: String?,
    @ColumnInfo(name = FilmTable.Columns.BACKDROP) val backdrop: String?,
    @ColumnInfo(name = FilmTable.Columns.DESCRIPTION) val description: String,
    @ColumnInfo(name = FilmTable.Columns.RATING) val rating: Double,
    @ColumnInfo(name = FilmTable.Columns.YEAR) val year: LocalDate?,
    @ColumnInfo(name = FilmTable.Columns.IS_FAVORITES) val isFavorites: Boolean = false
) : Parcelable

@Parcelize
@Entity(tableName = FilmRemoteTable.NAME)
data class FilmRemoteKeys(
    @ColumnInfo(name = FilmRemoteTable.Columns.FILM_ID) @PrimaryKey val filmId: Int,
    @ColumnInfo(name = FilmRemoteTable.Columns.PREV_KEY) val prevKey: Int?,
    @ColumnInfo(name = FilmRemoteTable.Columns.NEXT_KEY) val nextKey: Int?
) : Parcelable
