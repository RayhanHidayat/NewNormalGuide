package com.rayhan.newnormalguide.ui.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rayhan.newnormalguide.databinding.FragmentGuideBinding

class GuideFragment : Fragment(), GuideRecyclerViewClickListener {

    private lateinit var binding: FragmentGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!= null) {
            with(binding) {
                rvCovid.setHasFixedSize(true)
                rvCovid.layoutManager = LinearLayoutManager(context)

                val adapter = GuideAdapter(GuideContent.generateDataCovid())
                rvCovid.adapter = adapter
                adapter.listener = this@GuideFragment

            }
        }
    }

    override fun onItemClicked(data: GuideData) {
        showSelectedData(data)
    }

    private fun showSelectedData(data: GuideData) {

    }
}