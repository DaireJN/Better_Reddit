package com.daire.betterreddit.presentation.subreddits

import androidx.lifecycle.*
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.domain.usecase.GetDefaultSubredditListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SubredditsViewModel @Inject constructor(
    private val getDefaultSubredditListUseCase: GetDefaultSubredditListUseCase
) : ViewModel() {

    private val _state = MutableLiveData(SubredditListState())

    // provide read only state to outside components
    val state: LiveData<SubredditListState> = _state

    fun getDefaultSubreddits() {
        if (_state.value?.subredditData?.children == null) {
            getDefaultSubredditListUseCase.execute().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = SubredditListState(subredditData = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = SubredditListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = SubredditListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}