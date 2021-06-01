package com.rayhan.newnormalguide.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rayhan.newnormalguide.R
import com.rayhan.newnormalguide.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navHostController = navHostFragment.navController

        val actionBarConf = AppBarConfiguration(listFragment.fragmentSet)
        setupActionBarWithNavController(navHostController, actionBarConf)

        binding.bottomNavigationView.setupWithNavController(navHostController)
    }
}