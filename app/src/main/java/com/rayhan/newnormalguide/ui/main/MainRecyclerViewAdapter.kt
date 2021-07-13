package com.rayhan.newnormalguide.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rayhan.newnormalguide.databinding.ListItemCovidBinding
import com.rayhan.newnormalguide.ui.guide.GuideData
import com.rayhan.newnormalguide.ui.guide.GuideRecyclerViewClickListener

class MainRecyclerViewAdapter(private val list: MutableList<GuideData>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    lateinit var listener: GuideRecyclerViewClickListener

    inner class ViewHolder(private val listItemCovidBinding: ListItemCovidBinding) :
        RecyclerView.ViewHolder(listItemCovidBinding.root) {

        fun bind(data: GuideData) {
            with(listItemCovidBinding) {
                tvTitle.text = data.title
                tvJns.text = data.type

                Glide.with(itemView.context).load(data.symbolImg).into(imageCovid)
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