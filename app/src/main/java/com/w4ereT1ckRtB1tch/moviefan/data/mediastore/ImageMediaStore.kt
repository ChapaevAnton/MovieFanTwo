package com.w4ereT1ckRtB1tch.moviefan.data.mediastore

import android.graphics.Bitmap
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

interface ImageMediaStore {

    suspend fun downloadImageUrl(imageUrl: String?): Bitmap

    fun saveImageToGallery(image: Bitmap, film: Film?)
}