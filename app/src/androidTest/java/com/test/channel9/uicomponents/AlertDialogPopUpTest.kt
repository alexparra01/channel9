package com.test.channel9.uicomponents

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.test.channel9.presentation.uicomponents.alertdialog.ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG
import com.test.channel9.presentation.uicomponents.alertdialog.ALERT_DIALOG_TEST_TAG
import com.test.channel9.presentation.uicomponents.alertdialog.AlertDialogPopUp
import com.test.channel9.presentation.uicomponents.theme.Channel9Theme
import org.junit.Rule
import org.junit.Test

class AlertDialogPopUpTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showAlertDialogTest() {
        composeTestRule.setContent {
            Channel9Theme {
                AlertDialogPopUp(isShowingDialog = true) {}
            }
        }
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(ALERT_DIALOG_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(ALERT_DIALOG_TEST_TAG).assertExists().assertIsDisplayed()

    }

    @Test
    fun clickOnButtonDismissTest() {
        composeTestRule.setContent {
            Channel9Theme {
                AlertDialogPopUp(isShowingDialog = true) {}
            }
        }
        composeTestRule.waitUntil { composeTestRule.onAllNodesWithTag(
            ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG).fetchSemanticsNodes().size == 1 }
        composeTestRule.onNodeWithTag(ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG).performClick()
        composeTestRule.onNodeWithTag(ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG).assertExists().assertIsDisplayed()
    }
}