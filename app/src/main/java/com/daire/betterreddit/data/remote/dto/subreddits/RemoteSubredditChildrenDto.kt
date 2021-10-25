package com.daire.betterreddit.data.remote.dto.subreddits

import com.daire.betterreddit.domain.model.subreddit.SubredditChildData

data class RemoteSubredditChildrenDto(
    val data: RemoteDataDto,
    val kind: String
)

fun RemoteSubredditChildrenDto.toSubRedditChildData() = SubredditChildData(
    title = data.title,
    displayName = data.display_name,
    subscribers = data.subscribers,
    headerImage = data.header_img ?: "",
    iconImage = data.icon_img,
    description = data.public_description
)