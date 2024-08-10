@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.pbws.newsapp.news

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pbws.newsapp.ui.theme.NewsAppTheme
import com.pbws.newsapp.news.composables.NewsItem
import com.pbws.newsapp.news.composables.SearchNewsView
import com.pbws.newsapp.news.composables.SourcesTap
import com.pbws.newsapp.newsdetails.navigateToNewsDetailsScreen

@Composable
fun NewsScreen(
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }
    NewsAppTheme {
        NewsContent(
            searchValue = searchValue,
            onSearchValueChanged = {searchValue = it},
            onSearchIconClick = {
                Log.e("TAG","onSourceSelected $it")
            },
            newsSources = list,
            onSourceSelected = {
                Log.e("TAG","onSourceSelected $it")
            },
            newsList = list,
            authorName = "Author Name",
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        ){
            navController.navigateToNewsDetailsScreen(0)
        }
    }
}
val list = listOf("orange","apple","mango","orange","apple","mango",
    "orange","apple","mango","orange","apple","mango")



@Composable
fun NewsContent(
    searchValue:String,
    onSearchValueChanged:(value:String)->Unit,
    onSearchIconClick:(searchValue:String)->Unit,
    newsSources:List<String>,
    onSourceSelected:(sourceId:String)->Unit,
    newsList:List<String>,
    authorName:String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onItemClick:()->Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
    ) {
        SearchNewsView(searchValue = searchValue, onSearchValueChanged ={onSearchValueChanged(it)}, onSearchIconClick = {onSearchIconClick(it)} )
        SourcesTap(sources = newsSources) {
            onSourceSelected(it)
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            items(10) {
                NewsItem(
                    authorName = authorName,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    id = it,
                    onItemClick = onItemClick
                )
            }
        }
    }
}









