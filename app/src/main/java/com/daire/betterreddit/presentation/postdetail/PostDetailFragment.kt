package com.daire.betterreddit.presentation.postdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.daire.betterreddit.R
import com.daire.betterreddit.common.PostType
import com.daire.betterreddit.common.viewBinding
import com.daire.betterreddit.databinding.FragmentPostDetailBinding
import com.daire.betterreddit.presentation.UIController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment(R.layout.fragment_post_detail) {

    private lateinit var uiController: UIController
    private val args: PostDetailFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentPostDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiController = activity as UIController

        handlePostType()
        args.apply {
            binding.potsTitleTxt.text = this.postTitle
        }
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

}