package com.daire.betterreddit.data.remote.dto.postdetail

data class RemoteRootDataDto(
    val after: Any,
    val before: Any,
    val children: List<RemoteChildrenDto>,
    val dist: Any,
    val geo_filter: String,
    val modhash: String
)