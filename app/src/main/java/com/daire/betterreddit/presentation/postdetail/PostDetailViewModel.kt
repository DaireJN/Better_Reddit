package com.daire.betterreddit.presentation.postdetail

import androidx.lifecycle.*
import com.daire.betterreddit.common.Constants
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.domain.usecase.posts.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData(PostDetailState())

    // provide read only state to outside components
    val state: LiveData<PostDetailState> = _state

    fun getPostDetails(subredditName: String, postId: String) {
        savePostIdAndSubredditName(articleId = postId, subredditName = subredditName)
        val restoredPostId = savedStateHandle.get<String>(Constants.articleIdKey) ?: postId
        val restoredSubredditName =
            savedStateHandle.get<String>(Constants.subredditNameKey) ?: subredditName


        if (_state.value?.postDetail == null) {
            postUseCases.getPostDetailsUseCase.execute(
                articleId = restoredPostId,
                subredditName = restoredSubredditName
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = PostDetailState(postDetail = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = PostDetailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = PostDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    // save and data to be restored in case process death occurs
    private fun savePostIdAndSubredditName(articleId: String, subredditName: String) {
        savedStateHandle.set(Constants.articleIdKey, articleId)
        savedStateHandle.set(Constants.subredditNameKey, subredditName)
    }

}