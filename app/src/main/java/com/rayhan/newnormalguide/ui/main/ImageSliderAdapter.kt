package com.rayhan.newnormalguide.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.rayhan.newnormalguide.databinding.SliderItemBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter(private val list: ArrayList<String>) :
    SliderViewAdapter<ImageSliderAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SliderItemBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(image: String) {
            Glide.with(itemView.context).load(image).into(binding.imageView)
        }
    }

    override fun getCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder = ViewHolder(
        SliderItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(list[position])
}