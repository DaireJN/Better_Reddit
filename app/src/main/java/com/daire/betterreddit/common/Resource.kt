package com.daire.betterreddit.common

// wrapper class for holding network and cache loading states
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

const val HTTP_ERROR_MSG = "An unexpected error occurred"
const val IO_ERROR_MSG = "Couldn't reach server. Check your internet connection."