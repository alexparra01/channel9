package com.test.channel9.presentation.uicomponents.loaging

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

const val LOADING_TEST_TAG = "loading_test_tag"

@Composable
fun LoadingScreen(
    isVisible: Boolean
) {
    AnimatedVisibility(visible = isVisible) {
        Column(
            modifier = Modifier.fillMaxSize().testTag(LOADING_TEST_TAG),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    }
}