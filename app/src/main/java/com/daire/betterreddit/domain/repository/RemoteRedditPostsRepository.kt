package com.daire.betterreddit.domain.repository

import com.daire.betterreddit.data.remote.dto.posts.RemoteSubredditPostsResponseDto
import com.daire.betterreddit.data.remote.dto.subreddits.RemoteDefaultSubredditsDto

interface RemoteRedditPostsRepository {
    suspend fun getPosts(subredditName: String): RemoteSubredditPostsResponseDto
    suspend fun getDefaultSubreddits(): RemoteDefaultSubredditsDto
}