package com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters

import androidx.recyclerview.widget.DiffUtil
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

class FilmDiffUtilCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

}