package com.rayhan.newnormalguide.ui.detail_stats

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rayhan.newnormalguide.R
import com.rayhan.newnormalguide.data.api.ApiData
import com.rayhan.newnormalguide.databinding.ActivityDetailStatsBinding
import com.robinhood.ticker.TickerUtils
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailStatsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STATS = ""
    }

    private lateinit var currentlyShownData: List<ApiData>
    private lateinit var region: String
    private lateinit var adapter: ChartSparkAdapter
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

        region = intent.getStringExtra(EXTRA_STATS).toString()

        if (region.contentEquals("Nation")) {
            detStatsViewModel.nationData.observe(this, {
                renderAllData(it)
            })
        } else {
            detStatsViewModel.statesData.observe(this, {
                renderAllData(it)
            })
        }

    }

    private fun renderAllData(it: Map<String, List<ApiData>>?) {
        binding.run {
//            tickerData.setCharacterLists(TickerUtils.provideNumberList())
//            currentlyShownData = it
//
//            adapter = ChartSparkAdapter(it)
//            svChart.adapter = adapter
//
//            tvTitle.text = region
//            rbPositive.isChecked = true
//            rbMonth.isChecked = true
//
//            updateDisplayChart(ChartTypes.POSITIVE)
//            setUpEventListener()
        }
    }

    private fun renderAllData(it: List<ApiData>) {
        binding.run {
            tickerData.setCharacterLists(TickerUtils.provideNumberList())
            currentlyShownData = it

            adapter = ChartSparkAdapter(it.reversed())
            svChart.adapter = adapter

            tvTitle.text = region
            rbPositive.isChecked = true
            rbMonth.isChecked = true

            updateDisplayChart(ChartTypes.POSITIVE)
            setUpEventListener()
        }
    }

    private fun setUpEventListener() {

        binding.svChart.isScrubEnabled = true
        binding.svChart.setScrubListener {
            if (it is ApiData) {
                renderDisplayWithData(it)
            }
        }

        binding.rgTime.setOnCheckedChangeListener { _, checkedId ->
            adapter.time = when (checkedId) {
                binding.rbWeek.id -> TimeStamps.WEEK
                binding.rbMonth.id -> TimeStamps.MONTH
                else -> TimeStamps.MAX
            }

            renderDisplayWithData(currentlyShownData.last())
            adapter.notifyDataSetChanged()
        }
        binding.rgCase.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbNegative.id -> updateDisplayChart(ChartTypes.NEGATIVE)
                binding.rbPositive.id -> updateDisplayChart(ChartTypes.POSITIVE)
                binding.rbDeath.id -> updateDisplayChart(ChartTypes.DEATH)
            }
        }
    }

    private fun updateDisplayChart(types: ChartTypes) {
        val colorResult = when (types) {
            ChartTypes.NEGATIVE -> R.color.colorNegative
            ChartTypes.POSITIVE -> R.color.colorPositive
            ChartTypes.DEATH -> R.color.colorDeath
        }

        @ColorInt
        val colorInt = ContextCompat.getColor(this, colorResult)
        binding.svChart.lineColor = colorInt
        binding.tickerData.setTextColor(colorInt)

        adapter.type = types
        adapter.notifyDataSetChanged()

        renderDisplayWithData(currentlyShownData.last())
    }

    private fun renderDisplayWithData(data: ApiData) {
        val typeCases = when (adapter.type) {
            ChartTypes.POSITIVE -> data.positiveIncrease
            ChartTypes.NEGATIVE -> data.negativeIncrease
            ChartTypes.DEATH -> data.deathIncrease
        }
        binding.tickerData.text = NumberFormat.getInstance().format(typeCases)
        val displayDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        binding.tvDate.text = displayDateFormat.format(data.dateChecked)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}