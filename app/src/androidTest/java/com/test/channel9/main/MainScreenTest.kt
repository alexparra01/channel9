package com.test.channel9.main

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.channel9.presentation.main.MainActivity
import com.test.channel9.presentation.main.NEWS_ARTICLES_TEST_TAG
import com.test.channel9.presentation.uicomponents.loaging.LOADING_TEST_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    
    @Test
    fun showLoadingStateTest(){
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(LOADING_TEST_TAG).assertExists().assertIsDisplayed()
    }

    @Test
    fun showListOfArticlesTest(){
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(NEWS_ARTICLES_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(NEWS_ARTICLES_TEST_TAG).onChildren().onFirst().assertIsDisplayed()
    }
}