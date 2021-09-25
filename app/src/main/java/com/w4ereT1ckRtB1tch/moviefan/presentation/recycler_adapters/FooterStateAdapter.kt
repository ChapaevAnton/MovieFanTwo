package com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemFilmStateBinding


class FooterStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterStateAdapter.FooterStateHolder>() {

    override fun onBindViewHolder(holder: FooterStateHolder, loadState: LoadState) {
        holder.onBind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFilmStateBinding>(
            inflater,
            R.layout.item_film_state,
            parent,
            false
        )
        return FooterStateHolder(binding)
    }

    inner class FooterStateHolder(private val binding: ItemFilmStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(loadState: LoadState, retryCallback: () -> Unit) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    error.text = loadState.error.localizedMessage
                }
                progress.visibility =
                    if (loadState is LoadState.Loading) View.VISIBLE else View.INVISIBLE
                error.visibility =
                    if (loadState is LoadState.Error) View.VISIBLE else View.INVISIBLE
                retry.visibility =
                    if (loadState is LoadState.Error) View.VISIBLE else View.INVISIBLE
                retry.setOnClickListener { retryCallback.invoke() }
            }
        }
    }
}