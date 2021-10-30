package com.daire.betterreddit.domain.model.subreddit

data class SubredditChildData(
    val title: String,
    val subscribers: Int,
    val displayName: String,
    val headerImage: String,
    val iconImage: String,
    val description: String,
    val subredditName: String
)
