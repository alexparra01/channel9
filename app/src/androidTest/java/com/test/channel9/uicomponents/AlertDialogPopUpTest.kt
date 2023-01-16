package com.test.channel9.uicomponents

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.test.channel9.presentation.uicomponents.alertdialog.ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG
import com.test.channel9.presentation.uicomponents.alertdialog.ALERT_DIALOG_TEST_TAG
import com.test.channel9.presentation.uicomponents.alertdialog.AlertDialogPopUp
import com.test.channel9.presentation.uicomponents.theme.Channel9Theme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlertDialogPopUpTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Before
    fun setup(){
        composeTestRule.setContent {
            Channel9Theme {
                AlertDialogPopUp(isShowingDialog = true) {}
            }
        }
    }

    @Test
    fun showAlertDialogTest() {
        composeTestRule.onNodeWithTag(ALERT_DIALOG_TEST_TAG).assertTextEquals("Error!")
    }

    @Test
    fun clickOnButtonDismissTest() {
        composeTestRule.onNodeWithTag(ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG).performClick()
        composeTestRule.onNodeWithTag(ALERT_DIALOG_DISMISS_BUTTON_TEST_TAG).assertExists().assertIsDisplayed()
    }
}