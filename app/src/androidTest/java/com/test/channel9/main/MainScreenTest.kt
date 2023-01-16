package com.test.channel9.main

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.test.channel9.presentation.main.MainActivity
import com.test.channel9.presentation.main.NEWS_ARTICLES_TEST_TAG
import com.test.channel9.presentation.uicomponents.loaging.LOADING_TEST_TAG
import org.junit.Rule
import org.junit.Test


class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun t1_ShowLoadingStateTest(){
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(LOADING_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(LOADING_TEST_TAG).assertExists().assertIsDisplayed()
    }

    @Test
    fun t2_ShowListOfArticlesTest(){
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(NEWS_ARTICLES_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(NEWS_ARTICLES_TEST_TAG).onChildren().onFirst().assertIsDisplayed()
    }
}