package com.rayhan.newnormalguide.ui.detail_news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rayhan.newnormalguide.databinding.ActivityDetailNewsBinding
import com.rayhan.newnormalguide.ui.guide.GuideData
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class DetailNewsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEWS = ""
    }

    private lateinit var binding: ActivityDetailNewsBinding
    private lateinit var news: GuideData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        news = intent.getParcelableExtra(EXTRA_NEWS)!!

        with(binding) {
            Glide.with(this@DetailNewsActivity).load(news.urlImg)
                .apply(
                    RequestOptions.bitmapTransform(RoundedCornersTransformation(10, 0))
                )
                .into(imgNews)

            tvTitle.text = news.title
            tvSumber.text = news.source
            tvDokter.text = news.writer
            tvHeader.text = news.header
            tvContent.text = news.content
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}