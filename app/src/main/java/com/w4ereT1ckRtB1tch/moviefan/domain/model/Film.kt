package com.w4ereT1ckRtB1tch.moviefan.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
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
@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val filmId: Int,
    val title: String,
    val poster: String?,
    val backdrop: String?,
    val description: String,
    val rating: Double,
    val year: LocalDate?,
    var isFavorites: Boolean = false
) : Parcelable

@Parcelize
@Entity(tableName = "film_remote_keys")
data class FilmRemoteKeys(
    @PrimaryKey val filmId: Int,
    val prevKey: Int?,
    val nextKey: Int?
) : Parcelable
