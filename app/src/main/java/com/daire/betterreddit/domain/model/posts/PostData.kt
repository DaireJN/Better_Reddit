package com.daire.betterreddit.domain.model.posts

data class PostData(
    val score: Int,
    val title: String,
    val subreddit: String,
    val thumbnail: String,
    val author: String,
    val postHint: String,
    val url: String,
    val subredditNameWithPrefix: String
)
