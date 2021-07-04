package com.rayhan.newnormalguide.ui.detail_stats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rayhan.newnormalguide.databinding.ActivityDetailStatsBinding

class DetailStatsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STATS = ""
    }

    private lateinit var binding: ActivityDetailStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "Detail Statistic"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}