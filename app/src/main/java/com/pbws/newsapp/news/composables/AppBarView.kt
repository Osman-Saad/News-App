package com.pbws.newsapp.news.composables

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pbws.newsapp.R
import com.pbws.newsapp.category.CATEGORY_SCREEN_ROUTE
import com.pbws.newsapp.news.NEWS_SCREEN_ROUTE
import com.pbws.newsapp.newsdetails.NEWS_DETAILS_SCREEN_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    navHostController: NavHostController,
    onNavigationClick:()->Unit) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(8.dp),
        title = {
            Text(
                text = when(currentRoute){
                    CATEGORY_SCREEN_ROUTE -> "Categories"
                    NEWS_SCREEN_ROUTE -> "News"
                    "$NEWS_DETAILS_SCREEN_ROUTE/{id}" -> "News"
                    else -> "News App"
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
                    NEWS_SCREEN_ROUTE -> R.drawable.drawer_ic
                    "$NEWS_DETAILS_SCREEN_ROUTE/{id}" -> R.drawable.back_ic
                    else -> R.drawable.drawer_ic
                })
                , contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        when(currentRoute){
                            CATEGORY_SCREEN_ROUTE -> onNavigationClick()
                            NEWS_SCREEN_ROUTE -> onNavigationClick()
                            "$NEWS_DETAILS_SCREEN_ROUTE/{id}" -> navHostController.popBackStack()
                        }
                    },
            )
        }
    )
}