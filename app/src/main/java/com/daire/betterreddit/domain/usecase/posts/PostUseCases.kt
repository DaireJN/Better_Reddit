package com.daire.betterreddit.domain.usecase.posts

data class PostUseCases(
    val getPostDetailsUseCase: GetPostDetailsUseCase,
    val getPostsForSubredditUseCase: GetPostsForSubredditUseCase
)