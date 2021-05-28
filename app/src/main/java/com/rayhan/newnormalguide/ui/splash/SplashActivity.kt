package com.rayhan.newnormalguide.ui.splash

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rayhan.newnormalguide.R
import com.rayhan.newnormalguide.databinding.ActivitySplashBinding
import com.rayhan.newnormalguide.ui.home.HomeActivity
import splitties.activities.start

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slideTop = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        val slideBottom = AnimationUtils.loadAnimation(this, R.anim.splash_anim_text)

        // add animation
        with(binding){
            imgLogo.startAnimation(slideTop)
            tvLogo.startAnimation(slideBottom)
        }

        val splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        splashViewModel.liveData.observe(this, {
            when (it) {
                is SplashState.HomeActivity -> goToMainActivity()
            }
        })
    }

    private fun goToMainActivity() {
        finish()
        start<HomeActivity> { }
    }
}