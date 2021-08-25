package com.w4ereT1ckRtB1tch.moviefan.ui.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.data.Film
import com.w4ereT1ckRtB1tch.moviefan.ui.custom.view.RatingCircleView
import com.w4ereT1ckRtB1tch.moviefan.ui.home.HomeCatalogFilmAdapter.ItemFilmHolder
import com.bumptech.glide.Glide
import com.w4ereT1ckRtB1tch.moviefan.R

class HomeCatalogFilmAdapter(private val onItemClickListener: OnItemFilmClickListener) :
    RecyclerView.Adapter<ItemFilmHolder>() {

    private var itemsFilm = listOf<Film>()

    fun addItems(itemsFilm: List<Film>) {
        this.itemsFilm = itemsFilm

    }

    fun updateDataItems(itemsFilm: List<Film>) {
        this.itemsFilm = itemsFilm
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

        @RequiresApi(Build.VERSION_CODES.O)
        fun onBindItemFilm(film: Film?, onItemClickListener: OnItemFilmClickListener) {

            film?.let { it ->
                title.text = it.title
                Glide.with(itemView).load(it.poster).centerCrop().into(poster)
                ratingCircle.setProgress(it.rating.times(10).toInt())
                year.text = it.year.year.toString()
                favorites.setImageResource(if (it.isFavorites) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24)
            }

            itemView.setOnClickListener {
                if (film != null) {
                    onItemClickListener.onClickItem(film)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ItemFilmHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemFilmHolder, position: Int) {
        holder.onBindItemFilm(itemsFilm[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return itemsFilm.size
    }

}