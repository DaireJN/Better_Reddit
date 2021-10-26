package com.daire.betterreddit.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.daire.betterreddit.R
import com.daire.betterreddit.databinding.ActivityMainBinding
import com.daire.betterreddit.presentation.util.fadeToGone
import com.daire.betterreddit.presentation.util.fadeToInvisible
import com.daire.betterreddit.presentation.util.fadeToVisible
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
        val bottomNavigationView = binding.activityMainBottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        val destinationChangedListener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                showHideBottomNavOnDestinationChanged(destination.label as String)
            }
//        navController.addOnDestinationChangedListener(destinationChangedListener)
    }

    private fun showHideBottomNavOnDestinationChanged(fragmentLabel: String) {
        when (fragmentLabel) {
            "fragment_subreddits" -> {
                showBottomNavigationView()
            }
            "fragment_profile" -> {
                showBottomNavigationView()
            }
            "fragment_posts" -> {
                showBottomNavigationView()
            }
            else -> {
                hideBottomNavigationView()
            }
        }
    }

    private fun showBottomNavigationView() {
        if (binding.activityMainBottomNavigationView.visibility != View.VISIBLE) {
            binding.activityMainBottomNavigationView.fadeToVisible()
        }
    }

    private fun hideBottomNavigationView() {
        if (binding.activityMainBottomNavigationView.visibility == View.VISIBLE) {
            binding.activityMainBottomNavigationView.fadeToGone()
        }
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