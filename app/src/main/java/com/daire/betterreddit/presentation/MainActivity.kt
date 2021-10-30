package com.daire.betterreddit.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.daire.betterreddit.R
import com.daire.betterreddit.databinding.ActivityMainBinding
import com.daire.betterreddit.presentation.util.fadeToInvisible
import com.daire.betterreddit.presentation.util.fadeToVisible
import com.daire.betterreddit.presentation.util.hide
import com.daire.betterreddit.presentation.util.show
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

        bottomNavigationView.setOnItemReselectedListener {
            // do nothing if menu item is reselected (prevents multiple network calls etc)
        }
        lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener { _, destination, arguments ->
                showHideBottomNavOnDestinationChanged(destination.id, arguments)
            }
        }
    }

    /**
     * when home is clicked on the bottom nav
     * [com.daire.betterreddit.presentation.posts.PostsFragment] arguments will have a default value
     * of subredditName = "all"
     * this means the subreddit "all" should be shown and bottom nav should be visible
     * for all other subreddits bottom nav should be hidden in [com.daire.betterreddit.presentation.posts.PostsFragment]
     */
    private fun showHideBottomNavOnDestinationChanged(destinationId: Int, arguments: Bundle?) {
        if (destinationId == R.id.postsFragment && arguments?.getString("subredditName", "")
            != "all"
        ) {
            binding.activityMainBottomNavigationView.hide()
            return
        }
        when (destinationId) {
            R.id.subredditsFragment -> {
                binding.activityMainBottomNavigationView.show()
            }
            R.id.profileFragment -> {
                binding.activityMainBottomNavigationView.show()
            }
            R.id.postsFragment -> {
                binding.activityMainBottomNavigationView.show()
            }
            else -> {
                binding.activityMainBottomNavigationView.hide()
            }
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