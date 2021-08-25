package com.W4ereT1ckRtB1tch.moviefan.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Film(
    val title: String,
    val poster: Int,
    val description: String,
    val rating: Float,
    val year: LocalDate,
    var isFavorites: Boolean = false
) : Parcelable