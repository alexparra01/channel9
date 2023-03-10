package com.test.channel9.presentation.articledetails

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import com.test.channel9.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

const val ARTICLE_DETAIL_TEST_TAG = "article_detail_test_tag"
const val ARTICLE_DETAIL_BACK_BUTTON_TEST_TAG = "article_detail_back_button_test_tag"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(
    url: String,
    navHostController: NavHostController
) {
    val articleUrl = rememberSaveable { mutableStateOf(url) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar (
                title = {
                    Text(
                        text = stringResource(id = R.string.app_bar_article_details)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navHostController.popBackStack() },
                        modifier = Modifier.testTag(ARTICLE_DETAIL_BACK_BUTTON_TEST_TAG)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        },
        content = { ArticleDetailsLayout(articleUrl.value) },
        modifier = Modifier.testTag(ARTICLE_DETAIL_TEST_TAG)
    )
}

@Composable
fun ArticleDetailsLayout(url: String) {

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}