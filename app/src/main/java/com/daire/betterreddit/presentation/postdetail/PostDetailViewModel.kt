package com.daire.betterreddit.presentation.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.daire.betterreddit.common.Constants
import com.daire.betterreddit.domain.usecase.GetPostsForSubredditUseCase
import com.daire.betterreddit.presentation.posts.PostsDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostsForSubredditUseCase: GetPostsForSubredditUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData(PostsDataState())

    // provide read only state to outside components
    val state: LiveData<PostsDataState> = _state

    private fun getPostDetails(postId: String) {
        savePostId(postId)
        val postId = savedStateHandle.get<String>(Constants.articleIdKey) ?: postId

    }

    // save and restore postId in case process death occurs
    private fun savePostId(articleId: String) {
        savedStateHandle.set(Constants.articleIdKey, articleId)
    }

}