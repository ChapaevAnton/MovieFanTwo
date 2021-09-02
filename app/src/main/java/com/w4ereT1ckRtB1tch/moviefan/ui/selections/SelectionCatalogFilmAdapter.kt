package com.w4ereT1ckRtB1tch.moviefan.ui.selections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.Film
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmBinding
import com.w4ereT1ckRtB1tch.moviefan.ui.listeners.OnItemClickListener

class SelectionCatalogFilmAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<SelectionCatalogFilmAdapter.ItemFilmHolder>() {

    private var items = listOf<Film>()

    fun addItems(listItems: List<Film>) {
        this.items = listItems
    }

    fun updateItems(listItems: List<Film>) {
        this.items = listItems
        notifyDataSetChanged()
    }

    inner class ItemFilmHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(film: Film?, onItemClickListener: OnItemClickListener) {
            binding.film = film
            binding.onItemClicked = onItemClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemFilmBinding>(inflater, R.layout.item_film, parent, false)
        return ItemFilmHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemFilmHolder, position: Int) {
        holder.onBind(items[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}