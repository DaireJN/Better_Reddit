package com.daire.betterreddit.common

enum class PostType(val hint: String) {
    VIDEO(hint = "hosted:video"),
    IMAGE(hint = "image"),
    TEXT(hint = "self")
}