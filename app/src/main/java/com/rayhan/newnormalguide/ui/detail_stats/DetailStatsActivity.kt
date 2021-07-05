package com.rayhan.newnormalguide.ui.detail_stats

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rayhan.newnormalguide.data.api.ApiData
import com.rayhan.newnormalguide.databinding.ActivityDetailStatsBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat

class DetailStatsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STATS = ""
    }

    private lateinit var binding: ActivityDetailStatsBinding
    private val detStatsViewModel by viewModels<DetStatsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "Detail Statistic"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        detStatsViewModel.getNationalData()
        detStatsViewModel.getStatesData()

        detStatsViewModel.nationData.observe(this, {

            binding.run {
                val adapter = ChartSparkAdapter(it.reversed())
                svChart.adapter = adapter

                rbPositive.isChecked = true
                rbMonth.isChecked = true

                renderDisplayWithData(it.last())
            }
        })
    }

    private fun renderDisplayWithData(data: ApiData) {
        binding.tvData.text = NumberFormat.getInstance().format(data.positiveIncrease)
        val displayDateFormat = SimpleDateFormat("MMM dd, yyyy")
        binding.tvDate.text = displayDateFormat.format(data.dateChecked)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}