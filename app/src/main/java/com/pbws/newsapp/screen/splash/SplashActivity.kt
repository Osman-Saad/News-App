package com.pbws.newsapp.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pbws.newsapp.constant.ThemeConfig
import com.pbws.newsapp.screen.NewsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @Inject
    lateinit var themeConfig: ThemeConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isThemeDetected by remember {
                mutableStateOf(false)
            }
            var startAnimation by remember {
                mutableStateOf(false)
            }
            val alpha by animateFloatAsState(
                targetValue = if(startAnimation)1f else 0f,
                animationSpec = tween(
                    durationMillis = 3000
                ), label = ""
            )

            LaunchedEffect(true) {
                startAnimation = true
                isThemeDetected = themeConfig.preferencesFlow.first()
                delay(3000L)
                val intent = Intent(this@SplashActivity, NewsActivity::class.java)
                intent.putExtra("isDark",isThemeDetected)
                startActivity(intent)
                finish()
            }
            SplashScreen(alpha = alpha)
        }
    }
}
