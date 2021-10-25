package com.daire.betterreddit.domain.posts

data class PostData(
    val score: Int,
    val title: String,
    val subreddit: String,
    val thumbnail: String,
    val author: String
)
