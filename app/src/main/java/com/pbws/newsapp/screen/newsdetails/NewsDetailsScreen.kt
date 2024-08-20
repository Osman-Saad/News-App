package com.pbws.newsapp.screen.newsdetails

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.pbws.domain.model.ArticlesItem
import com.pbws.newsapp.R
import com.pbws.newsapp.screen.composables.AuthorView
import com.pbws.newsapp.screen.composables.LoadingView
import com.pbws.newsapp.screen.composables.TimePublishView
import com.pbws.newsapp.screen.news.searchedResultNews
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsDetailsScreen(
    navController: NavController,
    id: Int,
    newsDetailsViewModel: NewsDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val state = newsDetailsViewModel.state
    var articlesItem by rememberSaveable {
        mutableStateOf<ArticlesItem?>(null)
    }
    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = id) {
        if(searchedResultNews.isEmpty()){
            newsDetailsViewModel.channel.send(NewsDetailsIntent.GetNewsDetails(id))
        }else{
            articlesItem = searchedResultNews[id]
        }
    }

    remember {
        coroutineScope.launch {
            state.collect { state ->
                when (state) {
                    is NewsDetailsState.Error -> TODO()
                    NewsDetailsState.Ideal -> {}
                    NewsDetailsState.Loading -> {
                        isLoading = true
                    }

                    is NewsDetailsState.NewsRetrievedSuccess -> {
                        isLoading = false
                        articlesItem = state.news
                    }
                }
            }
        }
    }

    NewsDetailsContent(
        isLoading = isLoading,
        articlesItem = articlesItem,
        onLinkClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(it)
            }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_browser_found_to_open_this_link),
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
    )

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsDetailsContent(
    isLoading: Boolean,
    articlesItem: ArticlesItem?,
    onLinkClick: (link: String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoadingView()
        }

        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(articlesItem?.urlToImage)
                        .placeholder(R.drawable.placeholder)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(24.dp))
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                )
                Text(
                    text = articlesItem?.title ?: "",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AuthorView(authorName = articlesItem?.author ?: "")
                    TimePublishView(date = articlesItem?.publishedAt ?: "")
                }
                Text(
                    text = articlesItem?.description + "\n" + articlesItem?.content,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onPrimary,
                )

                Text(
                    text = articlesItem?.url ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        onLinkClick(articlesItem?.url ?: "")
                    }
                )
            }
        }
    }
}
