package com.daire.betterreddit.domain.model.postdetail

data class Reply(
    val author: String,
    val score: Int,
    val body: String,
    val createdUtc: Double
)
