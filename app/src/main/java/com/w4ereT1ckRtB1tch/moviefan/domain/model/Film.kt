package com.w4ereT1ckRtB1tch.moviefan.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val poster: String?,
    val backdrop: String?,
    val description: String,
    val rating: Double,
    val year: LocalDate?,
    var isFavorites: Boolean = false
) : Parcelable