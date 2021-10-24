package com.daire.betterreddit.data.remote.dto.posts

data class RemoteMediaEmbedDto(
    val content: String,
    val height: Int,
    val scrolling: Boolean,
    val width: Int
)