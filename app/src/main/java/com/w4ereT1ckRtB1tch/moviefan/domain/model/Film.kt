package com.w4ereT1ckRtB1tch.moviefan.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.w4ereT1ckRtB1tch.moviefan.data.db.AppDataBase.Companion.RemoteKeys
import com.w4ereT1ckRtB1tch.moviefan.data.db.AppDataBase.Companion.Films
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
@Entity(tableName = Films.NAME)
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = Films.Columns.FILM_ID) val filmId: Int,
    @ColumnInfo(name = Films.Columns.TITLE) val title: String,
    @ColumnInfo(name = Films.Columns.POSTER) val poster: String?,
    @ColumnInfo(name = Films.Columns.BACKDROP) val backdrop: String?,
    @ColumnInfo(name = Films.Columns.DESCRIPTION) val description: String,
    @ColumnInfo(name = Films.Columns.RATING) val rating: Double,
    @ColumnInfo(name = Films.Columns.YEAR) val year: LocalDate?,
    @ColumnInfo(name = Films.Columns.IS_FAVORITES) val isFavorites: Boolean = false
) : Parcelable

@Parcelize
@Entity(tableName = RemoteKeys.NAME)
data class FilmRemoteKeys(
    @ColumnInfo(name = RemoteKeys.Columns.FILM_ID) @PrimaryKey val filmId: Int,
    @ColumnInfo(name = RemoteKeys.Columns.PREV_KEY) val prevKey: Int?,
    @ColumnInfo(name = RemoteKeys.Columns.NEXT_KEY) val nextKey: Int?
) : Parcelable
