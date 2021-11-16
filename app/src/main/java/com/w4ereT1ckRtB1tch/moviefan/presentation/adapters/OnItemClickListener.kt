package com.w4ereT1ckRtB1tch.moviefan.presentation.adapters

import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

fun interface OnItemClickListener {
    fun onClick(film: Film?)
}