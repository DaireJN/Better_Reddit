package com.daire.betterreddit.data.remote.dto.postdetail

import com.daire.betterreddit.data.remote.dto.posts.RemoteReplyDto
import com.daire.betterreddit.domain.model.postdetail.Post
import com.daire.betterreddit.domain.model.postdetail.PostDetail
import com.daire.betterreddit.domain.model.postdetail.PostReply

class RootRemotePostDetailResponseDto : ArrayList<RemotePostDetailResponseDto>()

fun RootRemotePostDetailResponseDto.toPostDetail(): PostDetail {
    // post details are always in the first element
    val postDetails = this.first().data
    // comment details are always in the second element
    val commentDetails = this[1].data
    return PostDetail(
        post = Post(
            clicked = postDetails.children.first().data.clicked,
            createdUtc = postDetails.children.first().data.created_utc,
            score = postDetails.children.first().data.score,
            title = postDetails.children.first().data.title ?: "",
            author = postDetails.children.first().data.author ?: "",
            selfText = postDetails.children.first().data.selftext ?: ""
        ),
        postReplies = commentDetails.children.map {
            PostReply(
                id = it.data.id,
                clicked = it.data.clicked,
                createdUtc = it.data.created_utc,
                score = it.data.score,
                title = it.data.title ?: "",
                author = it.data.author ?: "",
                commentBody = it.data.body ?: "",
                replies = emptyList()
            )
        }
    )
}