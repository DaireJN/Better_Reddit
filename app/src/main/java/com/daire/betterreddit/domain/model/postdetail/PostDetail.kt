package com.daire.betterreddit.domain.model.postdetail

data class PostDetail(
    val title: String,
    val selfText: String,
    val clicked: Boolean,
    val score: Int,
    val createdUtc: Double,
    val replies: List<Reply>,
    val author: String
)