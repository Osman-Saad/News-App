
package com.pbws.newsapp.screen.composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pbws.data.model.ArticlesItemDto
import com.pbws.domain.model.ArticlesItem
import com.pbws.newsapp.R

@Composable
fun NewsItem(
    articlesItem:ArticlesItem,
    onItemClick:(id:Int)->Unit,

    ) {
    ElevatedCard(
        onClick = {onItemClick(articlesItem.articleId)},
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(articlesItem.urlToImage)
                    .crossfade(true)
                    .placeholder(R.drawable.placeholder)
                    .build(),
                placeholder = painterResource(id =R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                 contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Text(
                text = articlesItem.title?.trim()?:"",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                AuthorView(authorName = articlesItem.author?.trim()?:"",
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(end = 8.dp))
                TimePublishView(articlesItem.publishedAt?.trim()?:"")
            }

        }
    }
}


/*with(sharedTransitionScope){
        ElevatedCard(
            onClick = {onItemClick},
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .sharedElement(
                        state = rememberSharedContentState(key = "${SharedElementKeyConstant.NEWS_DETAILS_AUTHOR_NAME}${articlesItem.articleId}"),
                        animatedVisibilityScope = animatedContentScope
                    )
            ) {
                AsyncImage(
                    model = articlesItem.urlToImage,
                    placeholder = painterResource(id = R.drawable.placeholder)
                    , contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )

                Text(
                    text = articlesItem.title?:"",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp)
                ) {
                    AuthorView(authorName = articlesItem.author?:"")
                    Spacer(modifier = Modifier.weight(1f))
                    TimePublishView(articlesItem.publishedAt?:"")
                }

            }
        }
    }*/