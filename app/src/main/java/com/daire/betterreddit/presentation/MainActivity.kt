package com.daire.betterreddit.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.daire.betterreddit.R
import com.daire.betterreddit.databinding.ActivityMainBinding
import com.daire.betterreddit.presentation.util.fadeToInvisible
import com.daire.betterreddit.presentation.util.fadeToVisible
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

    override fun displayToast(message: String, length: Int?) {
        Toast.makeText(this, message, length ?: Toast.LENGTH_SHORT).show()
    }
}