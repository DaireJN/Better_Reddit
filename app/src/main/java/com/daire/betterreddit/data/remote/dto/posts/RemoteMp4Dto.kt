package com.daire.betterreddit.data.remote.dto.posts

data class RemoteMp4Dto(
    val resolutions: List<RemoteResolutionDto>,
    val source: RemoteSourceDto
)