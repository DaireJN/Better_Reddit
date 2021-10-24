package com.daire.betterreddit.data.remote.dto.posts

data class RemotePreviewDto(
    val enabled: Boolean,
    val images: List<RemoteImageDto>,
    val reddit_video_preview: RemoteRedditVideoPreviewDto
)