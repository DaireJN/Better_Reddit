package com.daire.betterreddit.data.remote.api

import com.daire.betterreddit.data.remote.dto.posts.RemoteSubredditPostsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RedditApi {
    @GET("/r/{subredditName}/hot.json")
    suspend fun getPostsForSubreddit(
        @Path("subredditName")
        subredditName: String
    ): RemoteSubredditPostsResponseDto
}