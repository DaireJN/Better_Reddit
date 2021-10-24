package com.daire.betterreddit.domain.posts

data class Data(
    val after: String,
    // before token will be null when requesting first set of results
    val before: String? = "",
    val children: List<Children>
)