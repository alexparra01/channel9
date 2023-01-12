package com.test.channel9.domain.models.states.navigation

const val MAIN_SCREEN_VIEW = "main_screen_view"
const val ARTICLE_DETAILS_SCREEN_VIEW = "article_details_screen_view"

sealed class AppNavigationState(val route: String){
    object MainScreenView: AppNavigationState(MAIN_SCREEN_VIEW)
    object ArticleDetailsScreenView: AppNavigationState(ARTICLE_DETAILS_SCREEN_VIEW)
}
