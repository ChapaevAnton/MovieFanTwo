package com.w4ereT1ckRtB1tch.moviefan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.data.Film
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmMiniBinding

class ListRecommendAdapter : RecyclerView.Adapter<ListRecommendAdapter.ListRecommendHolder>() {

    private var items = listOf<Film>()

    fun addItems(itemsFilm: List<Film>) {
        this.items = itemsFilm
    }

    fun updateItems(itemsFilm: List<Film>) {
        this.items = itemsFilm
        notifyDataSetChanged()
    }

    inner class ListRecommendHolder(private val binding: ItemFilmMiniBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(film: Film?) {
            binding.film = film
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRecommendHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFilmMiniBinding>(
            inflater,
            R.layout.item_film_mini,
            parent,
            false
        )
        return ListRecommendHolder(binding)
    }

    override fun onBindViewHolder(holder: ListRecommendHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}