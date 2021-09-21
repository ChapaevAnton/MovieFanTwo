package com.w4ereT1ckRtB1tch.moviefan.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.w4ereT1ckRtB1tch.moviefan.presentation.customview.RatingCircleView
import java.time.LocalDate

object DataBindingAdapter {

    @BindingAdapter("srcGlide")
    @JvmStatic
    fun setImage(@Nullable imageView: ImageView?, imageId: Int) {
        imageView?.let {
            Glide.with(it.context).load(imageId).centerCrop().into(it)
        }
    }

    @BindingAdapter("srcDrawable")
    @JvmStatic
    fun setImage(@Nullable floatingActionButton: FloatingActionButton?, drawable: Drawable) {
        floatingActionButton?.setImageDrawable(drawable)
    }

    @BindingAdapter("srcDrawable")
    @JvmStatic
    fun setImage(@Nullable imageView: ImageView?, drawable: Drawable) {
        imageView?.setImageDrawable(drawable)
    }

    @BindingAdapter("visibleState")
    @JvmStatic
    fun setVisibleState(@Nullable floatingActionButton: FloatingActionButton?, visible: Boolean) {
        floatingActionButton?.let {
            if (visible) it.show() else it.hide()
        }
    }

    @BindingAdapter("year")
    @JvmStatic
    fun setYear(@Nullable textView: TextView?, localDate: LocalDate) {
        textView?.text = localDate.year.toString()
    }

    @BindingAdapter("rating")
    @JvmStatic
    fun setRating(@Nullable ratingCircleView: RatingCircleView?, rating: Float) {
        ratingCircleView?.setProgress(rating.times(10).toInt())
    }

}
