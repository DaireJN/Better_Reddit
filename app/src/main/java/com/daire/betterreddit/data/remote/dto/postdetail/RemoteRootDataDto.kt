package com.daire.betterreddit.data.remote.dto.postdetail

import com.daire.betterreddit.domain.model.postdetail.PostDetail

data class RemoteRootDataDto(
    val after: String? = "",
    val before: String? = "",
    val children: List<RemoteChildrenDto>,
    val dist: Int,
    val geo_filter: String,
    val modhash: String
)

fun RemoteRootDataDto.toPostDetail() = PostDetail(
    clicked = children[0].data.clicked,
    createdUtc = children[0].data.created_utc,
    score = children[0].data.score,
    title = children[0].data.title,
    author = children[0].data.author,
    selfText = children[0].data.selftext,
    replies = children.map {
        it.toReply()
    }
)