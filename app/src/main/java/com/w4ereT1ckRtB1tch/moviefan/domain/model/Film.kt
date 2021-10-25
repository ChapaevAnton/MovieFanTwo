package com.w4ereT1ckRtB1tch.moviefan.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

// FIXME: 25.10.2021 refactoring model to
//  @kotlinx.android.parcel.Parcelize
//  data class Movies(
//  val total: Int = 0,
//  val page: Int = 0,
//  val movies: List<Movie>
//  ) : Parcelable

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
    @PrimaryKey val filmId: Long,
    val prevKey: Int?,
    val nextKey: Int?
) : Parcelable
