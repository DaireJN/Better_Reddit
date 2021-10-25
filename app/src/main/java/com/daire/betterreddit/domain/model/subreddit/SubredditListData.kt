package com.daire.betterreddit.domain.model.subreddit

data class SubredditListData(
    val after: String,
    val before: String,
    val children: List<SubredditChildData>
)