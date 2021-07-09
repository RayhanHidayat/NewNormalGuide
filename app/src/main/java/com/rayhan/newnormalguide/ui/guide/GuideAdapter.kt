package com.rayhan.newnormalguide.ui.guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rayhan.newnormalguide.databinding.ListItemCovidBinding
import com.rayhan.newnormalguide.ui.stats.GuideRecyclerViewClickListener

class GuideAdapter(private val list: MutableList<GuideData>) :
    RecyclerView.Adapter<GuideAdapter.ViewHolder>() {

    lateinit var listener: GuideRecyclerViewClickListener

    inner class ViewHolder(private val listItemCovidBinding: ListItemCovidBinding) :
        RecyclerView.ViewHolder(listItemCovidBinding.root) {

        fun bind(data: GuideData) {
            with(listItemCovidBinding) {
                tvTitle.text = data.title

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemCovidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener {
            listener.onItemClicked(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

}