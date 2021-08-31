package com.w4ereT1ckRtB1tch.moviefan.domain

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Film(
    val title: String,
    val poster: Int,
    val description: String,
    val rating: Float,
    val year: LocalDate,
    private var isFavorites: Boolean = false
) : Parcelable {

    @IgnoredOnParcel
    val observableIsFavorites = ObservableBoolean(isFavorites)

    @IgnoredOnParcel
    var favorites: Boolean
        get() {
            return isFavorites
        }
        set(value) {
            isFavorites = value
            observableIsFavorites.set(value)
        }
}