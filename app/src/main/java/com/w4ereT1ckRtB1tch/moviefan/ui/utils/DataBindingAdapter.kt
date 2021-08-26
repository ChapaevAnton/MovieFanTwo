package com.w4ereT1ckRtB1tch.moviefan.ui.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

    @BindingAdapter("visibleState")
    @JvmStatic
    fun setVisibleState(@Nullable floatingActionButton: FloatingActionButton?, visible: Boolean) {
        floatingActionButton?.let {
            if (visible) it.show() else it.hide()
        }
    }

}
