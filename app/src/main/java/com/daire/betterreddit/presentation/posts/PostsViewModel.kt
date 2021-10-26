package com.daire.betterreddit.presentation.posts

import androidx.lifecycle.*
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.domain.usecase.GetPostsForSubredditUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsForSubredditUseCase: GetPostsForSubredditUseCase
) : ViewModel() {

    private val _state = MutableLiveData(PostsDataState())

    // provide read only state to outside components
    val state: LiveData<PostsDataState> = _state

    fun getSubredditPosts(subredditName: String) {
        if (_state.value?.subredditData?.data?.children.isNullOrEmpty()) {
            getPostsForSubredditUseCase.execute(subredditName).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = PostsDataState(subredditData = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = PostsDataState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = PostsDataState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}
