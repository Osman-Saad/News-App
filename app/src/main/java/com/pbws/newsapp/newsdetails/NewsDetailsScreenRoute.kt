@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pbws.newsapp.newsdetails

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val NEWS_DETAILS_SCREEN_ROUTE = "news_details_screen"

fun NavController.navigateToNewsDetailsScreen(id:Int) {
    navigate("${NEWS_DETAILS_SCREEN_ROUTE}/$id")
}

fun NavGraphBuilder.newsDetailsScreenRoute(
    sharedTransitionScope: SharedTransitionScope,
    navHostController: NavHostController) {
    composable(
        route = "$NEWS_DETAILS_SCREEN_ROUTE/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType; defaultValue = 0 })
    ) {backStackEntry ->
        val id = backStackEntry.arguments?.getInt("id")
//        val snack = listSnacks[id!!]
        NewsDetailsScreen(
            navController = navHostController,
            id = id!!,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this@composable
        )
    }
}