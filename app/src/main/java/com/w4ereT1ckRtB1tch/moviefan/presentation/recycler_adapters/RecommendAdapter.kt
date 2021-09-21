package com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.domain.Film
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmMiniBinding

class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.ListRecommendHolder>() {

    var items: List<Film> = emptyList()
        set(newValue) {
            field = newValue
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