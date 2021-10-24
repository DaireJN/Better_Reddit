package com.daire.betterreddit.data.remote.dto.posts

data class RemoteRedditVideoPreviewDto(
    val bitrate_kbps: Int,
    val dash_url: String,
    val duration: Int,
    val fallback_url: String,
    val height: Int,
    val hls_url: String,
    val is_gif: Boolean,
    val scrubber_media_url: String,
    val transcoding_status: String,
    val width: Int
)