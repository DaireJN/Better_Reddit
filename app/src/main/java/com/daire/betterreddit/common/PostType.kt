package com.daire.betterreddit.common

enum class PostType(val hint: String) {
    HOSTED_VIDEO(hint = "hosted:video"),
    RICH_VIDEO(hint = "rich:video"),
    IMAGE(hint = "image"),
    TEXT(hint = "self"),
    NONE(hint = "")
}