package com.daire.betterreddit.data.remote.dto.posts

data class RemoteImageDto(
    val id: String,
    val resolutions: List<RemoteResolutionDto>,
    val source: RemoteSourceDto,
    val variants: RemoteVariantsDto
)