package com.daire.betterreddit.domain.usecase

import com.daire.betterreddit.common.HTTP_ERROR_MSG
import com.daire.betterreddit.common.IO_ERROR_MSG
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.data.remote.dto.posts.toSubRedditPostsData
import com.daire.betterreddit.domain.posts.SubRedditPostsData
import com.daire.betterreddit.domain.repository.RemoteRedditPostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPostsForSubredditUseCase @Inject constructor(
    private val repository: RemoteRedditPostsRepository
) {
    fun execute(subredditName: String): Flow<Resource<SubRedditPostsData>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getPosts(subredditName).toSubRedditPostsData()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error<SubRedditPostsData>(e.localizedMessage ?: HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error<SubRedditPostsData>(IO_ERROR_MSG))
        }
    }
}