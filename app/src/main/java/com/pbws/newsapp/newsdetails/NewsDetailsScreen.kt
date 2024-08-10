package com.pbws.newsapp.newsdetails

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pbws.newsapp.R
import com.pbws.newsapp.constant.SharedElementKeyConstant
import com.pbws.newsapp.news.composables.AuthorView
import com.pbws.newsapp.news.composables.TimePublishView
import com.pbws.newsapp.ui.theme.NewsAppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsDetailsScreen(
    navController: NavHostController,
    id: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    NewsAppTheme {
        NewsDetailsContent(
            navController = navController,
            id = id,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope
        )
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsDetailsContent(
    navController: NavHostController,
    id: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.test_img),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(24.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .sharedElement(
                        state = rememberSharedContentState(key = "${SharedElementKeyConstant.NEWS_DETAILS_IMAGE}$id"),
                        animatedVisibilityScope = animatedContentScope
                    )
            )
            Text(
                text = "Crystals dancing to the tune of light might " +
                        "replace batteries",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "${SharedElementKeyConstant.NEWS_DETAILS_TITLE}$id"),
                        animatedVisibilityScope = animatedContentScope
                    )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AuthorView(
                    authorName = "Osman Saad",
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "${SharedElementKeyConstant.NEWS_DETAILS_AUTHOR_NAME}$id"),
                        animatedVisibilityScope = animatedContentScope
                    ))
                TimePublishView(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = "${SharedElementKeyConstant.NEWS_DETAILS_TIME}$id"),
                        animatedVisibilityScope = animatedContentScope
                    )
                )
            }
            Text(
                text = t,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            Text(
                text = " \"https://www.wired.com/story/bitcoin-bros-go-wild-for-donald-trump/",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}


val t =
    "This innovative material, described in a study published in Nature Materials, opens doors to energy-efficient and wirelessly controlled systems, paving the way for advancements in robotics, aerospace, and biomedical devices.\n" +
            "\n" +
            "Beyond Traditional Actuators\n" +
            "\n" +
            "Traditional methods of converting energy often involve multiple stages, leading to inefficiencies and the added bulk of energy stores such as batteries.\n" +
            "However, the new photomechanical material developed by CU Boulder scientists  This innovative material, described in a study published in Nature Materials, opens doors to energy-efficient and wirelessly controlled systems, paving the way for advancements in robotics, aerospace, and biomedical devices.\\n\" +\n" +
            "        \"\\n\" +\n" +
            "        \"Beyond Traditional Actuators\\n\" +\n" +
            "        \"\\n\" +\n" +
            "        \"Traditional methods of converting energy often involve multiple stages, leading to inefficiencies and the added bulk of energy stores such as batteries.\\n\" +\n" +
            "        \"However, the new photomechanical material developed by CU Boulder scientists  This innovative material, described in a study published in Nature Materials, opens doors to energy-efficient and wirelessly controlled systems, paving the way for advancements in robotics, aerospace, and biomedical devices.\\n\" +\n" +
            "        \"\\n\" +\n" +
            "        \"Beyond Traditional Actuators\\n\" +\n" +
            "        \"\\n\" +\n" +
            "        \"Traditional methods of converting energy often involve multiple stages, leading to inefficiencies and the added bulk of energy stores such as batteries.\\n\" +\n" +
            "        \"However, the new photomechanical material developed by CU Boulder scientists "