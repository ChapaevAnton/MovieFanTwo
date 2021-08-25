package com.W4ereT1ckRtB1tch.moviefan.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.W4ereT1ckRtB1tch.moviefan.data.Film
import com.bumptech.glide.Glide
import com.w4eret1ckrtb1tch.moviefan.R

class ListRecommendAdapter : RecyclerView.Adapter<ListRecommendAdapter.ListRecommendHolder>() {

    private var itemsFilm = listOf<Film>()

    fun addItems(itemsFilm: List<Film>) {
        this.itemsFilm = itemsFilm
    }

    fun updateItems(itemsFilm: List<Film>) {
        this.itemsFilm = itemsFilm
        notifyDataSetChanged()
    }

    inner class ListRecommendHolder(itemFilmMini: View) : RecyclerView.ViewHolder(itemFilmMini) {

        private val poster: ImageView = itemFilmMini.findViewById(R.id.poster_film_mini)

        fun onBindItemFilm(film: Film?) {
            film?.let {
                Glide.with(itemView).load(it.poster).centerCrop().into(poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRecommendHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film_mini, parent, false)
        return ListRecommendHolder(view)
    }

    override fun onBindViewHolder(holder: ListRecommendHolder, position: Int) {
        holder.onBindItemFilm(itemsFilm[position])
    }

    override fun getItemCount(): Int {
        return itemsFilm.size
    }
}