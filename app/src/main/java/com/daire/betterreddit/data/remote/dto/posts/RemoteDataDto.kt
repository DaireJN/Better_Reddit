package com.daire.betterreddit.data.remote.dto.posts

import com.daire.betterreddit.domain.model.posts.Data
import com.google.gson.annotations.SerializedName

data class RemoteDataDto(
    val after: String,
    val before: String,
    @SerializedName("children")
    val remoteChildren: List<RemoteChildrenDto>,
    val dist: Int,
    val geo_filter: Any,
    val modhash: String
)

fun RemoteDataDto.toData() = Data(
    after = after,
    before = before,
    children = remoteChildren.map { remoteChild ->
        remoteChild.toChildren()
    }
)