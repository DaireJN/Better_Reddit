package com.daire.betterreddit.presentation.subbredditposts

import com.daire.betterreddit.domain.posts.SubRedditPostsData

data class SubredditPostsDataState(
    val isLoading: Boolean = false,
    val subredditData: SubRedditPostsData? = null,
    val error: String = ""
)