package com.mpokket.searchrepository

import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.TrendingRepositoriesBinding
import com.mpokket.models.Item

class SearchRepositoryViewHolder(private val dataBinding: TrendingRepositoriesBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(
        model: Item,
        position: Int
    ) {
        dataBinding.searchApiModel = model
        dataBinding.executePendingBindings()
    }
}