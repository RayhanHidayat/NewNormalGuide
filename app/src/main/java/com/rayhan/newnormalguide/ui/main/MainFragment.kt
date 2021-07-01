package com.rayhan.newnormalguide.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rayhan.newnormalguide.databinding.FragmentMainBinding
import com.rayhan.newnormalguide.ui.detail_stats.DetailStatsActivity
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import splitties.fragments.start


class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val urls = UrlList.images()

            val adapter = ImageSliderAdapter(urls)

            with(binding) {
                cvStats.setOnClickListener(this@MainFragment)
                imageSlider.setSliderAdapter(adapter)
                imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
                imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                imageSlider.scrollTimeInSec = 2
                imageSlider.startAutoCycle()
            }

        }
    }

    override fun onClick(v: View?) {
        when(v){
            binding.cvStats -> start<DetailStatsActivity>()
        }
    }

}