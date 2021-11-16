package com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.ItemLoadStateErrorBinding


class FooterStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterStateAdapter.FooterStateHolder>() {

    override fun onBindViewHolder(holder: FooterStateHolder, loadState: LoadState) {
        holder.onBind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterStateHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemLoadStateErrorBinding>(
            inflater,
            R.layout.item_load_state_error,
            parent,
            false
        )
        return FooterStateHolder(binding)
    }

    inner class FooterStateHolder(private val binding: ItemLoadStateErrorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(loadState: LoadState, retryCallback: () -> Unit) {
            with(binding) {
                loadIsVisible = (loadState is LoadState.Loading)
                errorIsVisible = (loadState is LoadState.Error)
                if (loadState is LoadState.Error) {
                    errorMessage.text = loadState.error.localizedMessage
                }
                retryLoad.setOnClickListener { retryCallback.invoke() }
            }
        }
    }
}