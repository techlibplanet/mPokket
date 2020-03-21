package com.mpokket.contributors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpokket.databinding.ContributorsItemBinding
import com.mpokket.models.ContributorsModel

class ContributorsAdapter : RecyclerView.Adapter<ContributorsViewHolder>() {

    var items: List<ContributorsModel> = emptyList()
    private lateinit var context: Context
    private lateinit var dataBinding: ContributorsItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContributorsViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        dataBinding = ContributorsItemBinding.inflate(inflater, parent, false)
        return ContributorsViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContributorsViewHolder, position: Int) {
        holder.bind(items[position], context)
    }
}