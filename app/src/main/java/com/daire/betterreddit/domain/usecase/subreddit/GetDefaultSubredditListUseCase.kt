package com.daire.betterreddit.domain.usecase.subreddit

import com.daire.betterreddit.common.HTTP_ERROR_MSG
import com.daire.betterreddit.common.IO_ERROR_MSG
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.data.remote.dto.subreddits.toSubRedditListData
import com.daire.betterreddit.domain.model.subreddit.SubredditListData
import com.daire.betterreddit.domain.repository.RemoteRedditPostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDefaultSubredditListUseCase @Inject constructor(
    private val repository: RemoteRedditPostsRepository
) {
    fun execute(): Flow<Resource<SubredditListData>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getDefaultSubreddits().data.toSubRedditListData()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error<SubredditListData>(e.localizedMessage ?: HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error<SubredditListData>(IO_ERROR_MSG))
        }
    }
}