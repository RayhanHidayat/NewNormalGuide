package com.rayhan.newnormalguide.ui.detail_stats

import com.rayhan.newnormalguide.data.api.ApiData
import com.robinhood.spark.SparkAdapter

class ChartSparkAdapter(private val listData: List<ApiData>) : SparkAdapter() {

    override fun getCount() = listData.size

    override fun getItem(index: Int) = listData[index]

    override fun getY(index: Int): Float {
        val chosenDayData = listData[index]
        return chosenDayData.positiveIncrease.toFloat()
    }

}