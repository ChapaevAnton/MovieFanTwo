package com.w4ereT1ckRtB1tch.moviefan.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

object FilmComparator : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}