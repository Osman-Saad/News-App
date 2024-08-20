package com.pbws.newsapp.screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pbws.newsapp.R
import com.pbws.newsapp.screen.category.CATEGORY_SCREEN_ROUTE
import com.pbws.newsapp.screen.news.NEWS_SCREEN_ROUTE
import com.pbws.newsapp.screen.news.NewsScreenArgs
import com.pbws.newsapp.screen.newsdetails.ID_ARGS
import com.pbws.newsapp.screen.newsdetails.NEWS_DETAILS_SCREEN_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    navController: NavController,
    onNavigationClick:()->Unit) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(8.dp),
        title = {
            Text(
                text = when(currentRoute){
                    CATEGORY_SCREEN_ROUTE -> stringResource(R.string.categories)
                    "$NEWS_SCREEN_ROUTE/{${NewsScreenArgs.CATEGORY_ARGS}}" -> stringResource(R.string.news)
                    "$NEWS_DETAILS_SCREEN_ROUTE/{$ID_ARGS}" -> stringResource(R.string.news)
                    else -> stringResource(R.string.news_app)
                },
                color = Color.Red,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = Color.Red,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        navigationIcon = {
            Icon(
                painter = painterResource(id = when(currentRoute){
                    CATEGORY_SCREEN_ROUTE -> R.drawable.drawer_ic
                    "$NEWS_SCREEN_ROUTE/{${NewsScreenArgs.CATEGORY_ARGS}}" -> R.drawable.drawer_ic
                    "$NEWS_DETAILS_SCREEN_ROUTE/{${ID_ARGS}}" -> R.drawable.back_ic
                    else -> R.drawable.drawer_ic
                })
                , contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        when (currentRoute) {
                            CATEGORY_SCREEN_ROUTE -> onNavigationClick()
                            "$NEWS_SCREEN_ROUTE/{${NewsScreenArgs.CATEGORY_ARGS}}" -> onNavigationClick()
                            "$NEWS_DETAILS_SCREEN_ROUTE/{${ID_ARGS}}" -> navController.popBackStack()
                        }
                    },
            )
        }
    )
}