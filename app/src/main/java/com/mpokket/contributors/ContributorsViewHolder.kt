package com.mpokket.contributors

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.ContributorsItemBinding
import com.mpokket.models.ContributorsModel

class ContributorsViewHolder(private val dataBinding: ContributorsItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(
        model: ContributorsModel,
        context: Context
    ) {
        dataBinding.contributorsModel = model

        itemView.setOnClickListener {
            context.startActivity(
                Intent(context, ContributorInfoActivity::class.java).putExtra(
                    "contributor_info",
                    model.url
                )
            )
        }
    }
}