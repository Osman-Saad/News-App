@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pbws.newsapp.news

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val NEWS_SCREEN_ROUTE = "news_screen"

fun NavController.navigateToNewsScreen(){
    navigate(NEWS_SCREEN_ROUTE)
}

fun NavGraphBuilder.newsScreenRoute(
    navHostController: NavHostController,
    sharedTransitionScope: SharedTransitionScope
){
    composable(route = NEWS_SCREEN_ROUTE) {
        NewsScreen(
            navController = navHostController,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this@composable
        )
    }
}