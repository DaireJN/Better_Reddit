package com.daire.betterreddit.data.remote.dto.postdetail

import com.daire.betterreddit.domain.model.postdetail.Reply

data class RemoteChildrenDto(
    val data: RemoteDataDto,
    val kind: String
)

fun RemoteChildrenDto.toReply() = Reply(
    createdUtc = data.created_utc,
    score = data.score,
    body = data.body,
    author = data.author
)