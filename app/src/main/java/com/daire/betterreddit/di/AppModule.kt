package com.daire.betterreddit.di

import com.daire.betterreddit.common.Constants
import com.daire.betterreddit.data.remote.api.RedditApi
import com.daire.betterreddit.data.remote.repository.RemoteRedditPostsRepositoryImpl
import com.daire.betterreddit.domain.repository.RemoteRedditPostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRedditApi(): RedditApi {
        return Retrofit.Builder()
            .baseUrl(Constants.REDDIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RedditApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRedditPostsRepository(api: RedditApi): RemoteRedditPostsRepository {
        return RemoteRedditPostsRepositoryImpl(api)
    }
}