package com.daire.betterreddit.data.remote.dto.posts

data class RemoteSecureMediaEmbedDto(
    val content: String,
    val height: Int,
    val media_domain_url: String,
    val scrolling: Boolean,
    val width: Int
)