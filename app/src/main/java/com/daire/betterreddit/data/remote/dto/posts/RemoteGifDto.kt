package com.daire.betterreddit.data.remote.dto.posts

data class RemoteGifDto(
    val resolutions: List<RemoteResolutionDto>,
    val source: RemoteSourceDto
)