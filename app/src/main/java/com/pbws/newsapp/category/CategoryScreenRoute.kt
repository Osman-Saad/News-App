package com.pbws.newsapp.category

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CATEGORY_SCREEN_ROUTE = "category_screen"

fun NavController.navigateToCategoryScreen() {
    navigate(CATEGORY_SCREEN_ROUTE)
}

fun NavGraphBuilder.categoryScreenRoute(navController: NavController) {
    composable(route = CATEGORY_SCREEN_ROUTE) {
        CategoryScreen(navController)
    }
}