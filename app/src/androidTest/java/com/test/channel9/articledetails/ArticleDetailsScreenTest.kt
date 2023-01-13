package com.test.channel9.articledetails

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.channel9.presentation.articledetails.ARTICLE_DETAIL_BACK_BUTTON_TEST_TAG
import com.test.channel9.presentation.articledetails.ARTICLE_DETAIL_TEST_TAG
import com.test.channel9.presentation.main.MainActivity
import com.test.channel9.presentation.main.NEWS_ARTICLES_TEST_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDetailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun showArticleDetailsTest(){
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(NEWS_ARTICLES_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(NEWS_ARTICLES_TEST_TAG).onChildren().onFirst().performClick()
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(ARTICLE_DETAIL_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(ARTICLE_DETAIL_TEST_TAG).assertIsDisplayed()
    }

    @Test
    fun navigateBackToMainScreenTest(){
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(NEWS_ARTICLES_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(NEWS_ARTICLES_TEST_TAG).onChildren().onFirst().performClick()

        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(ARTICLE_DETAIL_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(ARTICLE_DETAIL_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ARTICLE_DETAIL_BACK_BUTTON_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ARTICLE_DETAIL_BACK_BUTTON_TEST_TAG).performClick()

        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(NEWS_ARTICLES_TEST_TAG).fetchSemanticsNodes().size == 1 }

    }
}