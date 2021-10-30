package com.daire.betterreddit.presentation.postdetail

import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.daire.betterreddit.R
import com.daire.betterreddit.common.PostType
import com.daire.betterreddit.common.viewBinding
import com.daire.betterreddit.databinding.FragmentPostDetailBinding
import com.daire.betterreddit.domain.model.postdetail.PostReply
import com.daire.betterreddit.presentation.UIController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment constructor(

) : Fragment(R.layout.fragment_post_detail) {

    private lateinit var uiController: UIController
    private val args: PostDetailFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentPostDetailBinding::bind)
    private val viewModel by viewModels<PostDetailViewModel>()
    private var postCommentsAdapter: PostCommentsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiController = activity as UIController

        handlePostType()
        args.apply {
            binding.potsTitleTxt.text = this.postTitle
            viewModel.getPostDetails(postId = args.articleId, subredditName = args.subredditName)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            uiController.displayProgressSpinner(state.isLoading)
            state.postDetail?.apply {
                setSelfText(this.post.selfText)
                this.postReplies?.let { setAdapter(it) }
            }
            if (state.error.isNotEmpty()) {
                uiController.displayToast(state.error)
            }
        }
    }

    private fun setAdapter(postReplies: List<PostReply>) {
        postCommentsAdapter = PostCommentsAdapter()
        postCommentsAdapter?.submitList(postReplies)
        binding.postCommentsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.postCommentsRecycler.adapter = postCommentsAdapter
    }

    private fun handlePostType() {
        when (args.postType) {
            PostType.IMAGE.hint -> {

            }
            PostType.VIDEO.hint -> {
            }
            PostType.TEXT.hint -> {
                setSelfText(args.selfText)
            }
            PostType.NONE.hint -> {
                setSelfText(args.selfText)
            }
        }
    }

    private fun setSelfText(selfText: String) {
        binding.postTextDetailsTxt.text = selfText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        postCommentsAdapter = null
    }
}