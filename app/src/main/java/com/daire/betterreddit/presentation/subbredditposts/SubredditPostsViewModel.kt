package com.daire.betterreddit.presentation.subbredditposts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.domain.usecase.GetPostsForSubredditUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubredditPostsViewModel @Inject constructor(
    private val getPostsForSubredditUseCase: GetPostsForSubredditUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SubredditPostsDataState())

    // provide read only state to outside components
    val state: StateFlow<SubredditPostsDataState> = _state

    fun getSubredditPosts(subredditName: String) {
        getPostsForSubredditUseCase.execute(subredditName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SubredditPostsDataState(subredditData = result.data)
                }
                is Resource.Error -> {
                    _state.value = SubredditPostsDataState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SubredditPostsDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
