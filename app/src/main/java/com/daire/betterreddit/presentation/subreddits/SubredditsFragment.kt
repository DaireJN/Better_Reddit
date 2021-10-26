package com.daire.betterreddit.presentation.subreddits

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.daire.betterreddit.R
import com.daire.betterreddit.common.viewBinding
import com.daire.betterreddit.databinding.FragmentSubredditsBinding
import com.daire.betterreddit.domain.model.subreddit.SubredditChildData
import com.daire.betterreddit.presentation.UIController
import com.daire.betterreddit.presentation.util.fadeToVisible
import com.daire.betterreddit.presentation.util.loadImage
import com.daire.betterreddit.presentation.util.setSpanToFillRemainder
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubredditsFragment : Fragment(R.layout.fragment_subreddits), SubredditItemClickListener {

    private val binding by viewBinding(FragmentSubredditsBinding::bind)
    private val viewModel by viewModels<SubredditsViewModel>()
    private var subredditsAdapter: SubredditsAdapter? = null

    private lateinit var uiController: UIController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDefaultSubreddits()

        uiController = activity as UIController

        viewModel.state.observe(viewLifecycleOwner) { state ->
            uiController.displayProgressSpinner(state.isLoading)
            state.subredditData?.children.apply {
                if (!this.isNullOrEmpty()) {
                    setAdapter(this)
                }
            }
            if (state.error.isNotEmpty()) {
                uiController.displayToast(state.error)
            }
        }
    }

    private fun setAdapter(children: List<SubredditChildData>?) {
        binding.apply {
            subredditsRecycler.apply {
                val myLayoutManager = GridLayoutManager(requireContext(), 2)
                subredditsAdapter = SubredditsAdapter(this@SubredditsFragment)
                subredditsAdapter?.stateRestorationPolicy =
                    RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
                subredditsAdapter?.submitList(children)
                myLayoutManager.setSpanToFillRemainder(2)
                layoutManager = myLayoutManager
                adapter = subredditsAdapter
                fadeToVisible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subredditsAdapter = null
    }

    override fun onItemSelected(position: Int, item: SubredditChildData) {
        uiController.displayToast("click")
    }

    override fun onItemLongClick(position: Int, item: SubredditChildData): Boolean {
        showSubRedditInfoDialog(item)
        return true
    }

    private fun showSubRedditInfoDialog(subredditChildData: SubredditChildData) {
        val dialog =
            MaterialDialog(requireContext()).customView(
                R.layout.subreddit_info_dialog,
                scrollable = true
            )
        dialog.cornerRadius(res = R.dimen.card_corner_radius)
        val customView = dialog.getCustomView()
        val subredditInfoImage = customView.findViewById<ImageView>(R.id.subredditInfoImage)
        val subredditInfoDescriptionText =
            customView.findViewById<MaterialTextView>(R.id.subredditDescriptionTxt)
        val subredditInfoSubCountText =
            customView.findViewById<MaterialTextView>(R.id.subscriberCountTxt)
        val subredditInfoViewSubredditBtn =
            customView.findViewById<MaterialButton>(R.id.viewSubredditPostsBtn)
        subredditInfoImage.loadImage(subredditChildData.iconImage, requireContext())
        subredditInfoDescriptionText.text = subredditChildData.description
        subredditInfoSubCountText.text =
            getString(R.string.subscriber_count, subredditChildData.subscribers)
        dialog.show()
        subredditInfoViewSubredditBtn.setOnClickListener {

        }
    }

}