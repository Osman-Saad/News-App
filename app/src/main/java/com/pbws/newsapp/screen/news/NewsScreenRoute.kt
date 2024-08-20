
package com.pbws.newsapp.screen.news

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val NEWS_SCREEN_ROUTE = "news_screen"

fun NavController.navigateToNewsScreen(name:String){
    navigate("$NEWS_SCREEN_ROUTE/$name")
}

fun NavGraphBuilder.newsScreenRoute(
    navController: NavController){
    composable(
        route = "$NEWS_SCREEN_ROUTE/{${NewsScreenArgs.CATEGORY_ARGS}}",
        arguments = listOf(navArgument("${NewsScreenArgs.CATEGORY_ARGS}"){
            type = NavType.StringType
        }),
    ) {
        NewsScreen(
            navController = navController,
        )
    }
}

class NewsScreenArgs(savedStateHandle: SavedStateHandle){
    val name:String = checkNotNull(savedStateHandle[CATEGORY_ARGS])
    companion object{
        const val CATEGORY_ARGS = "category_name"
    }
}