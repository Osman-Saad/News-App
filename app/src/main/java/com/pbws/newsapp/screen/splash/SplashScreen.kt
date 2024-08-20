package com.pbws.newsapp.screen.splash

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import com.pbws.newsapp.R
import com.pbws.newsapp.constant.ThemeConfig
import com.pbws.newsapp.screen.NewsActivity
import com.pbws.newsapp.screen.category.navigateToCategoryScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import surfaceDark
import surfaceLight
import javax.inject.Inject

@Composable
fun SplashScreen(alpha:Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) surfaceDark else surfaceLight ),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.news_aap_logo)
            , contentDescription =null,
            modifier = Modifier
                .size(350.dp)
                .alpha(alpha),
        )
    }
}