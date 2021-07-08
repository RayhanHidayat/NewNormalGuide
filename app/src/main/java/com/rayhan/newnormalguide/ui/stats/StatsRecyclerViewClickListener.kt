package com.rayhan.newnormalguide.ui.stats

import com.rayhan.newnormalguide.data.api.ApiData

interface StatsRecyclerViewClickListener {
    fun onItemClicked(apiData: ApiData)
}