package com.daire.betterreddit.presentation.posts

import androidx.lifecycle.*
import com.daire.betterreddit.common.Constants
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.domain.usecase.posts.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData(PostsDataState())

    // provide read only state to outside components
    val state: LiveData<PostsDataState> = _state

    fun getSubredditPosts(subredditName: String) {
        saveSubredditName(subredditName)
        val restoredSubredditName =
            savedStateHandle.get<String>(Constants.subredditNameKey) ?: subredditName
        if (_state.value?.subredditData?.data?.children.isNullOrEmpty()) {
            postUseCases.getPostsForSubredditUseCase.execute(restoredSubredditName)
                .onEach { result ->
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

    // save and data to be restored in case process death occurs
    private fun saveSubredditName(subredditName: String) {
        savedStateHandle.set(Constants.subredditNameKey, subredditName)
    }
}
