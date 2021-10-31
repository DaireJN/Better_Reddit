package com.daire.betterreddit.domain.model.posts

data class PostData(
    val score: Int,
    val title: String,
    val subreddit: String,
    val thumbnail: String,
    val author: String,
    val postHint: String,
    val url: String,
    val subredditNameWithPrefix: String,
    val numComments: Int,
    val selfText: String,
    val id: String,
    val videoThumbnailUrl: String,
    val videoUrl: String
)
