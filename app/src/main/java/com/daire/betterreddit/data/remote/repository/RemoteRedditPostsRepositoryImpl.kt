package com.daire.betterreddit.data.remote.repository

import com.daire.betterreddit.data.remote.api.RedditApi
import com.daire.betterreddit.data.remote.dto.posts.RemoteSubredditPostsResponseDto
import com.daire.betterreddit.domain.repository.RemoteRedditPostsRepository
import javax.inject.Inject

class RemoteRedditPostsRepositoryImpl @Inject constructor(
    private val redditApi: RedditApi
) : RemoteRedditPostsRepository {
    override suspend fun getPosts(subredditName: String): RemoteSubredditPostsResponseDto =
        redditApi.getPostsForSubreddit(subredditName)
}