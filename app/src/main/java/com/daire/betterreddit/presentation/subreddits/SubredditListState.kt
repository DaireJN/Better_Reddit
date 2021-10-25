package com.daire.betterreddit.presentation.subreddits

import com.daire.betterreddit.domain.model.posts.SubRedditPostsData
import com.daire.betterreddit.domain.model.subreddit.SubredditListData

data class SubredditListState (
        val isLoading: Boolean = false,
        val subredditData: SubredditListData? = null,
        val error: String = ""
)