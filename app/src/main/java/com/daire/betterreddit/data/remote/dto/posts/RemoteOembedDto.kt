package com.daire.betterreddit.data.remote.dto.posts

data class RemoteOembedDto(
    val author_name: String,
    val author_url: String,
    val height: Int,
    val html: String,
    val provider_name: String,
    val provider_url: String,
    val thumbnail_height: Int,
    val thumbnail_url: String,
    val thumbnail_width: Int,
    val title: String,
    val type: String,
    val version: String,
    val width: Int
)