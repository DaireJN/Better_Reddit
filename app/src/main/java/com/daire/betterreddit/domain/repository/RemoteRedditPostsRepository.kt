package com.daire.betterreddit.domain.repository

import com.daire.betterreddit.data.remote.dto.posts.RemoteSubredditPostsResponseDto

interface RemoteRedditPostsRepository {
    suspend fun getPosts(subredditName: String): RemoteSubredditPostsResponseDto
}