package com.rayhan.newnormalguide.ui.stats

import com.rayhan.newnormalguide.ui.guide.GuideData

object GuideContent {
    fun generateDataCovid(): MutableList<GuideData> {
        val data = mutableListOf<GuideData>()

        data.add(
            GuideData(
                null,
                "Mengenal Covid-19",
                null,
                "alodokter",
                "..",
                "..",
                ".."
            )
        )
        return data
    }
}