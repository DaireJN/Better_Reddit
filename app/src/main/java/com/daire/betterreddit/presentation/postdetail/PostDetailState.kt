package com.daire.betterreddit.presentation.postdetail

import com.daire.betterreddit.domain.model.postdetail.PostDetail

data class PostDetailState(
    val isLoading: Boolean = false,
    val postDetail: PostDetail? = null,
    val error: String = ""
)
