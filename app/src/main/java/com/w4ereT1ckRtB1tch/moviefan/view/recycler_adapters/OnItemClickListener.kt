package com.w4ereT1ckRtB1tch.moviefan.view.recycler_adapters

import com.w4ereT1ckRtB1tch.moviefan.domain.Film

fun interface OnItemClickListener {
    fun onClick(film: Film)
}