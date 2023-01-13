package com.test.channel9.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.test.channel9.R
import com.test.channel9.domain.models.Asset
import com.test.channel9.domain.models.states.navigation.AppNavigationState
import com.test.channel9.presentation.extensions.navigate
import com.test.channel9.presentation.uicomponents.alertdialog.AlertDialogPopUp
import com.test.channel9.presentation.uicomponents.gestures.rememberForeverLazyListState
import com.test.channel9.presentation.uicomponents.loaging.LoadingScreen
import com.test.channel9.presentation.util.Constants

const val OVERVIEW = "Overview"
const val NEWS_ARTICLES_TEST_TAG = "news_articles_test_tag"

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navHostController: NavHostController
) {
    LaunchedEffect(Unit){
        viewModel.fetchNewsArticles()
    }
    when(viewModel.state.collectAsState().value){
        is MainScreenViewModel.MainScreenState.ShowLoading -> {
            LoadingScreen(isVisible = true)
        }
        is MainScreenViewModel.MainScreenState.ShowData -> {
            LoadingScreen(isVisible = false)
            AlertDialogPopUp(isShowingDialog = false) {}
            NewsArticlesListLayout(viewModel, navHostController )
        }
        is MainScreenViewModel.MainScreenState.ShowError -> {
            LoadingScreen(isVisible = false)
            AlertDialogPopUp(isShowingDialog = true) { viewModel.fetchNewsArticles() }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticlesListLayout(viewModel: MainScreenViewModel, navHostController: NavHostController) {

    val articles = rememberSaveable { mutableStateOf(viewModel.articlesList()) }

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_bar_main_title),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = Color.White,
            ),
        )
        LazyColumn(
            state = rememberForeverLazyListState(key = OVERVIEW),
            modifier = Modifier.testTag(NEWS_ARTICLES_TEST_TAG)
        ) {
            items(
                articles.value
            ){
                NewsArticleItem(
                    asset = it,
                    viewModel = viewModel,
                    navHostController = navHostController
                )
            }
        }
    }

}

@Composable
fun NewsArticleItem(asset: Asset, viewModel: MainScreenViewModel, navHostController: NavHostController) {
    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .padding(2.dp)
            .clickable(onClick = {
                navHostController
                    .navigate(
                        AppNavigationState.ArticleDetailsScreenView.route,
                        Constants.ARTICLE_URL_KEY to asset.url
                    ) {
                        popUpTo(AppNavigationState.ArticleDetailsScreenView.route) {
                            inclusive = true
                        }
                    }
            }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = asset.headline,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                GlideImage(
                    modifier = Modifier
                        .width(102.dp)
                        .height(68.dp)
                        .padding(start = 5.dp, top = 3.dp),
                    imageModel = { viewModel.findThumbnailImage(asset.relatedImages).url },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    ),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = asset.theAbstract,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.Black,
                lineHeight = 18.sp,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.app_main_card_by_label),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray

                )
                Text(
                    text = asset.byLine,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray

                )
            }
        }
    }
}

