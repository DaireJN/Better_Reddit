package com.daire.betterreddit.domain.model.postdetail

data class PostDetail(
    val post: Post,
    val postReplies: List<PostReply>? = emptyList(),
)

class Post(
    val title: String,
    val selfText: String,
    val clicked: Boolean,
    val score: Int,
    val createdUtc: Double,
    val author: String
)

data class PostReply(
    val id: String,
    val title: String,
    val commentBody: String,
    val clicked: Boolean,
    val score: Int,
    val createdUtc: Double,
    val replies: List<Reply>,
    val author: String
)