package com.daire.betterreddit.data.remote.dto.subreddits

import com.daire.betterreddit.domain.model.subreddit.SubredditListData

data class RemoteSubredditDataDto(
    val after: String,
    val before: String? = "",
    val children: List<RemoteSubredditChildrenDto>,
    val dist: Int,
    val geo_filter: String,
    val modhash: String
)

fun RemoteSubredditDataDto.toSubRedditListData() = SubredditListData(
    after = after,
    before = before ?: "",
    children = children.map {
        it.toSubRedditChildData()
    }

)