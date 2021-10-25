package com.daire.betterreddit.data.remote.dto.posts

import com.daire.betterreddit.domain.posts.Child
import com.google.gson.annotations.SerializedName

data class RemoteChildrenDto(
    @SerializedName("data")
    val postData: RemotePostDataDto,
    val kind: String
)

fun RemoteChildrenDto.toChildren() = Child(
    kind = kind,
    postData = postData.toPostData()
)