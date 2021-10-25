package com.daire.betterreddit.data.remote.dto.posts

import com.daire.betterreddit.domain.model.posts.SubRedditPostsData

data class RemoteSubredditPostsResponseDto(
    val data: RemoteDataDto,
    val kind: String
)

fun RemoteSubredditPostsResponseDto.toSubRedditPostsData() = SubRedditPostsData(
    kind = kind,
    data = data.toData()
)