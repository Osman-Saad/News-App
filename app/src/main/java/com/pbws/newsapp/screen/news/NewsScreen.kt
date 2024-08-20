package com.pbws.newsapp.screen.news

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pbws.data.model.ArticlesItemDto
import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.model.SourcesItem
import com.pbws.newsapp.screen.composables.LoadingView
import com.pbws.newsapp.screen.composables.NewsItem
import com.pbws.newsapp.screen.composables.SearchNewsView
import com.pbws.newsapp.screen.composables.SourcesTap
import com.pbws.newsapp.screen.news.snackbarmessage.MessageHandelViewModel
import com.pbws.newsapp.screen.news.snackbarmessage.SnackBarIntent
import com.pbws.newsapp.screen.newsdetails.navigateToNewsDetailsScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel = hiltViewModel(),
    messageHandelViewModel: MessageHandelViewModel = hiltViewModel()
) {
    val state = newsViewModel.state
    val lifecycleOwner = LocalLifecycleOwner.current
    var newsSources by rememberSaveable {
        mutableStateOf<MutableList<SourcesItem>?>(null)
    }
    var isLoading by rememberSaveable {
        mutableStateOf(true)
    }
    var tabSelectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    var searchValue by rememberSaveable {
        mutableStateOf("")
    }
    var sourcesIsLoaded by rememberSaveable {
        mutableStateOf(false)
    }
    var newsList by rememberSaveable {
        mutableStateOf<List<ArticlesItem>?>(null)
    }
    var newsSearched by rememberSaveable {
        mutableStateOf<List<ArticlesItem>?>(null)
    }



    val coroutineScope = rememberCoroutineScope()
    remember {
        coroutineScope.launch {
            newsViewModel.state.observe(lifecycleOwner) { state ->
                when (state) {
                    is NewsScreenState.Loading -> {
                        isLoading = true
                    }

                    is NewsScreenState.SourcesRetrievedSuccess -> {
                        searchedResultNews  = emptyList<ArticlesItem>().toMutableList()
                        newsSources = state.sources.toMutableList()
                        if (tabSelectedIndex == 0 && newsSources.isNullOrEmpty().not()) {
                            coroutineScope.launch {
                                newsSources?.get(0)?.id?.let {
                                    newsViewModel.channel.send(NewsScreenIntent.GetNews(it))
                                }
                            }
                        }
                    }

                    is NewsScreenState.Error -> {
                        coroutineScope.launch {
                            messageHandelViewModel.channel.send(SnackBarIntent.Error(state.message))
                        }
                    }

                    NewsScreenState.Ideal -> {}

                    is NewsScreenState.NewsRetrievedSuccess -> {
                        isLoading = false
                        newsList = state.news
                    }
                    is NewsScreenState.SearchedNewsRetrievedSuccess -> {
                        isLoading = false
                        newsList = state.news.toMutableList()
                        searchedResultNews = state.news.toMutableList()
                    }

                    else -> {}
                }
            }
        }

    }

    if (!sourcesIsLoaded) {
        LaunchedEffect(key1 = newsViewModel.getCategory()) {
            coroutineScope.launch(Dispatchers.IO) {
                newsViewModel.channel.send(NewsScreenIntent.GetSources(newsViewModel.getCategory()))
                sourcesIsLoaded = true
            }
        }
    }

    NewsContent(
        searchValue = searchValue,
        onSearchValueChanged = {
            searchValue = it
            if(searchValue.isEmpty()){
                coroutineScope.launch {
                    newsViewModel.channel.send(NewsScreenIntent.GetSources(newsViewModel.getCategory()))
                }
            }
        },
        onSearchIconClick = {
            if(it.isNotEmpty()){
                coroutineScope.launch {
                    newsViewModel.channel.send(NewsScreenIntent.OnSearchQueryChange(it))
                }
            }
        },
        newsSources = newsSources,
        onSourceSelected = {
        },
        newsList = newsList ?: emptyList(),
        isLoading = isLoading,
        tabSelectedIndex = tabSelectedIndex,
        onTabSelected = {
            coroutineScope.launch {
                if (it != tabSelectedIndex) {
                    newsSources?.get(it)?.id?.let {
                        newsViewModel.channel.send(NewsScreenIntent.GetNews(it))
                    }
                    tabSelectedIndex = it
                }
            }

        },
    ) { id ->
        navController.navigateToNewsDetailsScreen(id)
    }
}




@Composable
fun NewsContent(
    searchValue: String,
    onSearchValueChanged: (value: String) -> Unit,
    onSearchIconClick: (searchValue: String) -> Unit,
    newsSources: List<SourcesItem>?,
    onSourceSelected: (sourceId: String) -> Unit,
    newsList: List<ArticlesItem>,
    isLoading: Boolean,
    tabSelectedIndex: Int,
    onTabSelected: (index: Int) -> Unit,
    onItemClick: (id: Int) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            LoadingView()
        }

        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
            ) {
                SearchNewsView(
                    searchValue = searchValue,
                    onSearchValueChanged = { onSearchValueChanged(it) },
                    onSearchIconClick = { onSearchIconClick(it) })
                if(searchedResultNews.isEmpty()){
                    SourcesTap(
                        sources = newsSources,
                        tabSelectedIndex = tabSelectedIndex,
                        onTabSelected = { onTabSelected(it) })
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                ) {
                    newsList?.let { newsList ->
                        items(items = newsList) { item ->
                            item?.let {
                                NewsItem(
                                    articlesItem = it,
                                    onItemClick = { onItemClick(it) }
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}






