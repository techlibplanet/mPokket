package com.mpokket.trendingrepository

import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.TrendingRepositoriesBinding
import com.mpokket.models.TrendingRepositoriesModel

class TrendingRepositoryViewHolder(private val dataBinding: TrendingRepositoriesBinding) :
    RecyclerView.ViewHolder(dataBinding.root)
{
    var rowIndex = -1
    fun bind(
        model: TrendingRepositoriesModel,
        position: Int
    ) {
        dataBinding.trendingRepository = model
        dataBinding.executePendingBindings()
    }
}