package com.w4ereT1ckRtB1tch.moviefan.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.presentation.customview.RatingCircleView
import java.time.LocalDate

object DataBindingAdapter {

    @BindingAdapter("srcGlide")
    @JvmStatic
    inline fun setImage(@Nullable imageView: ImageView?, imageId: Int) {
        imageView?.let {
            Glide.with(it.context).load(imageId).centerCrop().into(it)
        }
    }

    @BindingAdapter("srcGlide")
    @JvmStatic
    inline fun setImage(@Nullable imageView: ImageView?, @Nullable imageUrl: String?) {
        imageView?.let {
            Glide.with(it.context)
                .load(imageUrl)
                .placeholder(R.drawable.glide_load_image)
                .error(R.drawable.glide_load_image_error)
                .fallback(R.drawable.glide_load_image_error)
                .centerCrop()
                .into(it)
        }
    }

    @BindingAdapter("srcDrawable")
    @JvmStatic
    inline fun setImage(@Nullable floatingActionButton: FloatingActionButton?, drawable: Drawable) {
        floatingActionButton?.setImageDrawable(drawable)
    }

    @BindingAdapter("srcDrawable")
    @JvmStatic
    inline fun setImage(@Nullable imageView: ImageView?, drawable: Drawable) {
        imageView?.setImageDrawable(drawable)
    }

    @BindingAdapter("visibleState")
    @JvmStatic
    inline fun setVisibleState(
        @Nullable floatingActionButton: FloatingActionButton?,
        visible: Boolean
    ) {
        floatingActionButton?.let {
            if (visible) it.show() else it.hide()
        }
    }

    @BindingAdapter("year")
    @JvmStatic
    inline fun setYear(@Nullable textView: TextView?, localDate: LocalDate?) {
        textView?.let {
            it.text = localDate?.year?.toString() ?: it.context.getString(R.string.not_announced)
        }
    }

    @BindingAdapter("rating")
    @JvmStatic
    inline fun setRating(@Nullable ratingCircleView: RatingCircleView?, rating: Double) {
        ratingCircleView?.setProgress(rating.times(10).toInt())
    }

}
