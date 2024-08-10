
package com.pbws.newsapp.news.composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pbws.newsapp.R
import com.pbws.newsapp.constant.SharedElementKeyConstant

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsItem(
    authorName:String,
    id:Int,
    onItemClick:()->Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
    ) {
    with(sharedTransitionScope){
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .clickable(
                    onClick = {
                        onItemClick()
                    },
                    role = Role.Button
                ).sharedElement(
                    state = rememberSharedContentState(key = "${SharedElementKeyConstant.NEWS_DETAILS_AUTHOR_NAME}$id"),
                    animatedVisibilityScope = animatedContentScope
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.test_img) ,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(16.dp))
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = "US Counterintelligence warns of spy risks to  space technology",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Justify
                )
                AuthorView(authorName = authorName)
                Spacer(modifier = Modifier.weight(1f))
                TimePublishView()
            }
        }
    }

}