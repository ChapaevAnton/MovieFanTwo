package com.w4ereT1ckRtB1tch.moviefan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.data.Film
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmMiniBinding

class ListRecommendAdapter : RecyclerView.Adapter<ListRecommendAdapter.ListRecommendHolder>() {

    var items: List<Film> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
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
        val binding = ItemFilmMiniBinding.inflate(inflater, parent, false)
        return ListRecommendHolder(binding)
    }

    override fun onBindViewHolder(holder: ListRecommendHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}