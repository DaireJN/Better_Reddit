package com.daire.betterreddit.data.remote.repository

import com.daire.betterreddit.data.remote.api.RedditApi
import com.daire.betterreddit.data.remote.dto.postdetail.RootRemotePostDetailResponseDto
import com.daire.betterreddit.data.remote.dto.posts.RemoteSubredditPostsResponseDto
import com.daire.betterreddit.data.remote.dto.subreddits.RemoteDefaultSubredditsDto
import com.daire.betterreddit.domain.repository.RemoteRedditPostsRepository
import javax.inject.Inject

class RemoteRedditPostsRepositoryImpl @Inject constructor(
    private val redditApi: RedditApi
) : RemoteRedditPostsRepository {
    override suspend fun getPosts(subredditName: String): RemoteSubredditPostsResponseDto =
        redditApi.getPostsForSubreddit(subredditName)

    override suspend fun getDefaultSubreddits(): RemoteDefaultSubredditsDto =
        redditApi.getDefaultSubreddits()

    override suspend fun getPostDetails(
        subredditName: String,
        articleId: String
    ): RootRemotePostDetailResponseDto =
        redditApi.getPostDetails(subredditName, articleId)

}