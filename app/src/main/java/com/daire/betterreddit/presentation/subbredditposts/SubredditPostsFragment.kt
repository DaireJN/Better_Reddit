package com.daire.betterreddit.presentation.subbredditposts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daire.betterreddit.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SubredditFragment : Fragment(R.layout.fragment_subreddit) {

    private val viewModel by viewModels<SubredditPostsViewModel>()

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSubredditPosts("all")

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (state.isLoading) {
                        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                    }
                    if (!state.subredditData?.data?.children.isNullOrEmpty()) {
                        Toast.makeText(requireContext(), "data ready", Toast.LENGTH_SHORT).show()
                    }
                    if (state.error.isNotEmpty()) {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}