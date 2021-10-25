package com.daire.betterreddit.presentation.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daire.betterreddit.R
import com.daire.betterreddit.common.viewBinding
import com.daire.betterreddit.databinding.FragmentPostsBinding
import com.daire.betterreddit.domain.model.posts.Child
import com.daire.betterreddit.presentation.UIController
import com.daire.betterreddit.presentation.util.fadeToVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private val binding by viewBinding(FragmentPostsBinding::bind)
    private val viewModel by viewModels<PostsViewModel>()
    private var postsAdapter: PostsAdapter? = null
    private lateinit var uiController: UIController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSubredditPosts("all")

        uiController = activity as UIController

        viewModel.state.observe(viewLifecycleOwner) { state ->
            uiController.displayProgressSpinner(state.isLoading)
            state.subredditData?.data?.children.apply {
                if (!this.isNullOrEmpty()) {
                    setAdapter(this)
                }
            }
            if (state.error.isNotEmpty()) {
                uiController.displayToast(state.error)
            }
        }
    }

    private fun setAdapter(children: List<Child>) {
        binding.apply {
            postsRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity)
                postsAdapter = PostsAdapter()
                postsAdapter?.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                postsAdapter?.submitList(children)
                adapter = postsAdapter
                fadeToVisible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        postsAdapter = null
    }
}