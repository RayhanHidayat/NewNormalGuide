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
    private lateinit var statesDivision: Map<String, List<ApiData>>
    private lateinit var statesList: MutableList<ApiData>
    private val statsViewModel by viewModels<StatsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            statsViewModel.getStatesData()
            statsViewModel.statesData.observe(viewLifecycleOwner, {

                statesDivision = it.reversed().groupBy { it.state.toString() }
                val keys = statesDivision.keys.toMutableList()
                keys.sort()

                for (i in keys) {
                    statesList.add(statesDivision[i]!!.last())
                }
                val data = statesList

                renderRecycleList()
            })
        }
    }

    private fun renderRecycleList() {
        with(binding) {
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.adapter = StatsAdapter(statesList)
        }
    }

}