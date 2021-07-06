package com.rayhan.newnormalguide.ui.detail_stats

import android.graphics.RectF
import com.rayhan.newnormalguide.data.api.ApiData
import com.robinhood.spark.SparkAdapter

class ChartSparkAdapter(private val listData: List<ApiData>) : SparkAdapter() {

    var type = ChartTypes.POSITIVE
    var time = TimeStamps.MAX

    override fun getCount() = listData.size

    override fun getItem(index: Int) = listData[index]

    override fun getY(index: Int): Float {
        val chosenDayData = listData[index]
        return when (type) {
            ChartTypes.POSITIVE -> chosenDayData.positiveIncrease.toFloat()
            ChartTypes.NEGATIVE -> chosenDayData.negativeIncrease.toFloat()
            ChartTypes.DEATH -> chosenDayData.deathIncrease.toFloat()
        }
    }

    override fun getDataBounds(): RectF {
        val dataBounds = super.getDataBounds()
        if (time != TimeStamps.MAX) {
            dataBounds.left = count - time.numDays.toFloat()
        }
        return dataBounds
    }
}