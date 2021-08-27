package com.w4ereT1ckRtB1tch.moviefan.ui.listeners

import com.w4ereT1ckRtB1tch.moviefan.data.Film

fun interface OnItemClickListener {
    fun onClick(film: Film)
}