package com.w4ereT1ckRtB1tch.moviefan.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmBinding
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

class HomeAdapter(private val onItemClickListener: OnItemClickListener) :
    PagingDataAdapter<Film, HomeAdapter.ItemFilmHolder>(FilmComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemFilmBinding>(inflater, R.layout.item_film, parent, false)
        return ItemFilmHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemFilmHolder, position: Int) {
        holder.onBind(getItem(position), onItemClickListener)
    }

    class ItemFilmHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(film: Film?, onItemClickListener: OnItemClickListener) {
            film?.let {
                binding.film = it
                binding.onItemClicked = onItemClickListener
            }
        }
    }
}