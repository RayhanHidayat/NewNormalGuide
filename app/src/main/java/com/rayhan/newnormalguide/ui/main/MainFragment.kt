package com.rayhan.newnormalguide.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rayhan.newnormalguide.databinding.FragmentMainBinding
import com.rayhan.newnormalguide.ui.detail_stats.DetailStatsActivity
import com.robinhood.ticker.TickerUtils
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import splitties.fragments.start
import java.text.NumberFormat


class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

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

            mainViewModel.getNationalData()
            mainViewModel.nationData.observe(viewLifecycleOwner, {
                binding.run {
                    tickerPositif.setCharacterLists(TickerUtils.provideNumberList())
                    tickerSembuh.setCharacterLists(TickerUtils.provideNumberList())
                    tickerDeath.setCharacterLists(TickerUtils.provideNumberList())

                    tickerPositif.text = NumberFormat.getInstance().format(it.last().positive)
                    tickerSembuh.text = NumberFormat.getInstance().format(it.last().negative)
                    tickerDeath.text = NumberFormat.getInstance().format(it.last().death)
                }
            })

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
        when (v) {
            binding.cvStats -> start<DetailStatsActivity> {
                putExtra( DetailStatsActivity.EXTRA_STATS, "Nation")
            }
        }
    }

}