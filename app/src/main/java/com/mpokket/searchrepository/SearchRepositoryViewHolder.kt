package com.mpokket.searchrepository

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.TrendingRepositoriesBinding
import com.mpokket.models.Item
import com.mpokket.repodetails.RepoDetailsActivity

class SearchRepositoryViewHolder(private val dataBinding: TrendingRepositoriesBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(
        model: Item,
        context: Context
    ) {
        dataBinding.searchApiModel = model
        dataBinding.executePendingBindings()

        itemView.setOnClickListener {
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra("item", model)
            context.startActivity(intent)
        }
    }
}