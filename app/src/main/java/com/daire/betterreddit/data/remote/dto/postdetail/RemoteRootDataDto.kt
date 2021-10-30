package com.daire.betterreddit.data.remote.dto.postdetail

class RemoteRootDataDto(
    val after: String? = "",
    var children: List<RemoteChildrenDto>,
    val before: String? = "",
    val dist: Int,
    val geo_filter: String,
    val modhash: String
)