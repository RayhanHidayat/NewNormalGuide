package com.rayhan.newnormalguide.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rayhan.newnormalguide.data.api.ApiData
import com.rayhan.newnormalguide.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private val statsViewModel by viewModels<StatsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initiateData()
    }

    private fun initiateData() {
        statsViewModel.getStatesData()
        statsViewModel.statesData.observe(viewLifecycleOwner, {
            val statesList = it.keys.toMutableList()
            statesList.sort()

            renderRecycleList(it)
        })
    }

    private fun renderRecycleList(map: Map<String, List<ApiData>>) {
        binding.run {
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.adapter = StatsAdapter(map.keys)
        }
    }
}