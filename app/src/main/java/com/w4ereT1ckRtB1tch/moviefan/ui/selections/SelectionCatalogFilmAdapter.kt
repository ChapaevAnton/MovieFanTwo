package com.w4ereT1ckRtB1tch.moviefan.ui.selections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.data.Film
import com.w4ereT1ckRtB1tch.moviefan.ui.custom.view.RatingCircleView
import com.bumptech.glide.Glide
import com.w4ereT1ckRtB1tch.moviefan.R

class SelectionCatalogFilmAdapter(private val onItemFilmClickListener: OnItemFilmClickListener) :
    RecyclerView.Adapter<SelectionCatalogFilmAdapter.ItemFilmHolder>() {

    private var listItems = listOf<Film>()

    fun addItems(listItems: List<Film>) {
        this.listItems = listItems
    }

    fun updateItems(listItems: List<Film>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun interface OnItemFilmClickListener {
        fun onClickItem(film: Film)
    }

    inner class ItemFilmHolder(itemFilm: View) : RecyclerView.ViewHolder(itemFilm) {

        private val title: TextView = itemFilm.findViewById(R.id.title_film)
        private val poster: ImageView = itemFilm.findViewById(R.id.poster_film)
        private val ratingCircle: RatingCircleView = itemFilm.findViewById(R.id.rating_circle_film)
        private val year: TextView = itemFilm.findViewById(R.id.year_film)
        private val favorites: ImageView = itemFilm.findViewById(R.id.favorites_film)

        fun onBindItemFilm(film: Film?, onItemFilmClickListener: OnItemFilmClickListener) {
            film?.let {
                title.text = film.title
                Glide.with(itemView).load(it.poster).centerCrop().into(poster)
                ratingCircle.setProgress(it.rating.times(10).toInt())
                year.text = film.year.year.toString()
                favorites.setImageResource(if (film.isFavorites()) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24)
            }

            itemView.setOnClickListener {
                if (film != null) {
                    onItemFilmClickListener.onClickItem(film)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ItemFilmHolder(view)
    }

    override fun onBindViewHolder(holder: ItemFilmHolder, position: Int) {
        holder.onBindItemFilm(listItems[position], onItemFilmClickListener)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}