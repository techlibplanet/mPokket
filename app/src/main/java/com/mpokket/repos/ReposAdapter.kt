package com.mpokket.repos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.ReposItemBinding
import com.mpokket.models.RepoModel

class ReposAdapter : RecyclerView.Adapter<ReposViewHolder>() {

    var items: List<RepoModel> = emptyList()
    private lateinit var context: Context
    private lateinit var dataBinding: ReposItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReposViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        dataBinding = ReposItemBinding.inflate(inflater, parent, false)
        return ReposViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(items[position], context)
    }
}