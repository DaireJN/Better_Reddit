package com.daire.betterreddit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.daire.betterreddit.R
import com.daire.betterreddit.databinding.ActivityMainBinding
import com.daire.betterreddit.presentation.utli.fadeToInvisible
import com.daire.betterreddit.presentation.utli.fadeToVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UIController {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun displayProgressSpinner(shouldBeVisible: Boolean) {
        if (shouldBeVisible) {
            binding.progressBar.fadeToVisible()
        } else {
            binding.progressBar.fadeToInvisible()
        }
    }
}