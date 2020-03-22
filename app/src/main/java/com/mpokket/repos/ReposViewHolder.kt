package com.mpokket.repos

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.ReposItemBinding
import com.mpokket.models.Item
import com.mpokket.repodetails.RepoDetailsActivity

class ReposViewHolder(private val dataBinding: ReposItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(
        model: Item,
        context: Context
    ) {
        dataBinding.repoModel = model

        itemView.setOnClickListener {
            context.startActivity(
                Intent(context, RepoDetailsActivity::class.java).putExtra(
                    "item",
                    model
                )
            )
        }
    }
}