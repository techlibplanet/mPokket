package com.mpokket.repos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.ReposItemBinding
import com.mpokket.helper.Globals
import com.mpokket.models.RepoModel

class ReposViewHolder (private val dataBinding: ReposItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(
        model: RepoModel,
        context: Context
    ) {
        dataBinding.repoModel = model

        itemView.setOnClickListener {
//            context.startActivity(
//                Intent(context, ContributorInfoActivity::class.java).apply {
//                    putExtra("contributor_info", model.url)
//                    putExtra("repo_url", model.repos_url)
//                }
//            )
            Globals.showToastMessage(context, "Item Clicked")
        }
    }
}