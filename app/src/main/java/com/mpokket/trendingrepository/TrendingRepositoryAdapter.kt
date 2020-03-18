package com.mpokket.trendingrepository

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.TrendingRepositoriesBinding
import com.mpokket.models.TrendingRepositoriesModel

class TrendingRepositoryAdapter : RecyclerView.Adapter<TrendingRepositoryViewHolder>() {

    var items: List<TrendingRepositoriesModel> = emptyList()
    private lateinit var context: Context
    private lateinit var dataBinding: TrendingRepositoriesBinding
    var rowIndex = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingRepositoryViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        dataBinding = TrendingRepositoriesBinding.inflate(inflater, parent, false)
        return TrendingRepositoryViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrendingRepositoryViewHolder, position: Int) {
        holder.bind(items[position], position)
        holder.itemView.setOnClickListener {
            if (rowIndex == holder.adapterPosition) {
                rowIndex = -1
                dataBinding.expandableLayout.visibility = View.GONE
            } else {
                rowIndex = holder.adapterPosition
                notifyDataSetChanged()
            }
        }
        if (rowIndex < items.size) {
            if (rowIndex == holder.adapterPosition) {
                dataBinding.expandableLayout.visibility = View.VISIBLE
            } else {
                dataBinding.expandableLayout.visibility = View.GONE
            }
        }
    }
}