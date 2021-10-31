package com.daire.betterreddit.domain.usecase.posts

import com.bumptech.glide.load.HttpException
import com.daire.betterreddit.common.HTTP_ERROR_MSG
import com.daire.betterreddit.common.IO_ERROR_MSG
import com.daire.betterreddit.common.Resource
import com.daire.betterreddit.data.remote.dto.postdetail.toPostDetail
import com.daire.betterreddit.domain.model.postdetail.PostDetail
import com.daire.betterreddit.domain.repository.RemoteRedditPostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val remoteRedditPostsRepository: RemoteRedditPostsRepository
) {
    fun execute(subredditName: String, articleId: String): Flow<Resource<PostDetail>> = flow {
        try {
            emit(Resource.Loading())
            val data =
                remoteRedditPostsRepository.getPostDetails(subredditName, articleId).toPostDetail()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error<PostDetail>(e.localizedMessage ?: HTTP_ERROR_MSG))
        } catch (e: IOException) {
            emit(Resource.Error<PostDetail>(IO_ERROR_MSG))
        }
    }
}