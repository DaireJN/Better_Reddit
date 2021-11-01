package com.daire.betterreddit.presentation.postdetail

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.daire.betterreddit.R
import com.daire.betterreddit.testextensions.launchFragmentInHiltContainer
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostDetailFragmentTestSuite {
    @Test
    fun testPostDetailFragmentRecreation() {
        val fragmentArgs = bundleOf(
            "articleId" to "qjxe0y",
            "subredditName" to "all",
            "selfText" to "lorem",
            "postType" to "self",
            "upvoteCount" to 10,
            "postTitle" to "lorem"
        )

        launchFragmentInHiltContainer<PostDetailFragment>(
            fragmentArgs,
            R.style.Theme_MaterialComponents_DayNight
        )
        onView(withId(R.id.postTextDetailsTxt)).check(matches(not(withText(""))))
    }
}
