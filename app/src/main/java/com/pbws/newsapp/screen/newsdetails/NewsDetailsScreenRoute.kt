@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pbws.newsapp.screen.newsdetails

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val NEWS_DETAILS_SCREEN_ROUTE = "news_details_screen"
const val ID_ARGS = "id"

fun NavController.navigateToNewsDetailsScreen(id:Int) {
    navigate("$NEWS_DETAILS_SCREEN_ROUTE/$id")
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.newsDetailsScreenRoute(
    navController: NavController) {
    composable(
        route = "$NEWS_DETAILS_SCREEN_ROUTE/{$ID_ARGS}",
        arguments = listOf(navArgument(ID_ARGS) { type = NavType.IntType; defaultValue = 0 })
    ) {backStackEntry ->
        val id = backStackEntry.arguments?.getInt(ID_ARGS)
        NewsDetailsScreen(
            navController = navController,
            id = id!!,
        )
    }
}