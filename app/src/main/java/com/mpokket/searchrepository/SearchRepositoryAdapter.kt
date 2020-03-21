package com.mpokket.searchrepository

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.TrendingRepositoriesBinding
import com.mpokket.models.Item

class SearchRepositoryAdapter : RecyclerView.Adapter<SearchRepositoryViewHolder>() {

    var items: List<Item> = emptyList()
    private lateinit var context: Context
    private lateinit var dataBinding: TrendingRepositoriesBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRepositoryViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        dataBinding = TrendingRepositoriesBinding.inflate(inflater, parent, false)
        return SearchRepositoryViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchRepositoryViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
}