package com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmMiniBinding
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

class UpcomingPagingAdapter :
    PagingDataAdapter<Film, UpcomingPagingAdapter.ItemFilmHolder>(FilmComparator) {

    override fun onBindViewHolder(holder: ItemFilmHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmMiniBinding.inflate(inflater, parent, false)
        return ItemFilmHolder(binding)
    }

    inner class ItemFilmHolder(private val binding: ItemFilmMiniBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(film: Film?) {
            binding.film = film
        }
    }
}

object FilmComparator : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}