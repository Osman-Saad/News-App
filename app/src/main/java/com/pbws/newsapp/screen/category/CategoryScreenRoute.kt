package com.pbws.newsapp.screen.category

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val CATEGORY_SCREEN_ROUTE = "category_screen"

fun NavController.navigateToCategoryScreen() {
    navigate(CATEGORY_SCREEN_ROUTE)
}

fun NavGraphBuilder.categoryScreenRoute(navController: NavController) {
    composable(
        route = CATEGORY_SCREEN_ROUTE) {
        CategoryScreen(navController)
    }
}
