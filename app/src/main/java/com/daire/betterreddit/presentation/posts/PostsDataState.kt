package com.daire.betterreddit.presentation.posts

import com.daire.betterreddit.domain.model.posts.SubRedditPostsData

data class PostsDataState(
    val isLoading: Boolean = false,
    val subredditData: SubRedditPostsData? = null,
    val error: String = ""
)