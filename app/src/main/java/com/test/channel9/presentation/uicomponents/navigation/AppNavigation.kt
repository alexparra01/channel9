package com.test.channel9.presentation.uicomponents.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.channel9.domain.models.states.navigation.AppNavigationState
import com.test.channel9.presentation.articledetails.ArticleDetailsScreen
import com.test.channel9.presentation.extensions.requiredStringArg
import com.test.channel9.presentation.main.MainScreen
import com.test.channel9.presentation.main.MainScreenViewModel
import com.test.channel9.presentation.util.Constants


@Composable
fun AppNavigation(initialDestination: String) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination =  initialDestination
    ) {
        composable(route = AppNavigationState.MainScreenView.route) {
            MainScreen(
                viewModel = hiltViewModel(),
                navHostController = navController
            )
        }

        composable(route = AppNavigationState.ArticleDetailsScreenView.route) { navBackStackEntry ->
            ArticleDetailsScreen(
                url = navBackStackEntry.requiredStringArg(Constants.ARTICLE_URL_KEY),
                navHostController = navController
            )
        }
    }
}