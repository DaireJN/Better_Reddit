package com.daire.betterreddit.presentation.subreddits

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import com.daire.betterreddit.R
import com.daire.betterreddit.common.OkHttpProvider
import com.daire.betterreddit.presentation.MainActivity
import com.daire.betterreddit.testextensions.launchFragmentInHiltContainer
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit


class SubredditsFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val mockWebServer = MockWebServer()

    @Test
    fun run() {
        launchFragmentInHiltContainer<SubredditsFragment>(
            null,
            R.style.Theme_MaterialComponents_DayNight
        )
    }


    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody("OK")
            }
        }
    }

    @Test
    fun testFailedResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }

    }


    @Before
    fun setUp() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpClient()
            )
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}