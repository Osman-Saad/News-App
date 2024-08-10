package com.pbws.newsapp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pbws.newsapp.category.CATEGORY_SCREEN_ROUTE
import com.pbws.newsapp.category.categoryScreenRoute
import com.pbws.newsapp.news.newsScreenRoute
import com.pbws.newsapp.newsdetails.newsDetailsScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsNavHost(
  modifier: Modifier = Modifier,
  navHostController: NavHostController
) {
  SharedTransitionLayout {
    NavHost(
      navController = navHostController,
      startDestination = CATEGORY_SCREEN_ROUTE,
      modifier = modifier){
      categoryScreenRoute(navHostController)
      newsScreenRoute(
        sharedTransitionScope = this@SharedTransitionLayout,
        navHostController = navHostController)
      newsDetailsScreenRoute(
        sharedTransitionScope = this@SharedTransitionLayout,
        navHostController = navHostController)
    }
  }
}