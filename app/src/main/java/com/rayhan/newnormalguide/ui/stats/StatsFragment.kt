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
import com.rayhan.newnormalguide.ui.detail_stats.DetailStatsActivity
import splitties.fragments.start

class StatsFragment : Fragment(), StatsRecyclerViewClickListener {

    private lateinit var binding: FragmentStatsBinding
    private lateinit var statesDivision: Map<String, List<ApiData>>
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

                val statesList: MutableList<ApiData> = mutableListOf()
                for (i in keys) {
                    statesList.add(statesDivision.getValue(i).last())
                }

                val listAdapter = StatsAdapter(statesList)

                renderDataToRecycler(listAdapter)

            })
        }
    }

    private fun renderDataToRecycler(listAdapter: StatsAdapter) {
        with(binding) {
            rvData.setHasFixedSize(true)
            rvData.layoutManager = LinearLayoutManager(context)
            rvData.adapter = listAdapter
        }
        listAdapter.listener = this@StatsFragment
    }

    override fun onItemClicked(apiData: ApiData) {
        showSelectedData(apiData)
    }

    private fun showSelectedData(data: ApiData) {
        start<DetailStatsActivity> {
            putExtra(DetailStatsActivity.EXTRA_STATS, data.state)
        }
    }

}