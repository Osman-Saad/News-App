package com.pbws.newsapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import backgroundDark
import backgroundLight
import errorDark
import errorLight
import onBackgroundDark
import onBackgroundLight
import onErrorDark
import onErrorLight
import onPrimaryDark
import onPrimaryLight
import onSecondaryDark
import onSecondaryLight
import onSurfaceDark
import onSurfaceLight
import primaryDark
import primaryLight
import surfaceDark
import surfaceLight
import textColorSearchTexDark
import textColorSearchTextLight

 val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    background = backgroundDark,
    onTertiary = textColorSearchTexDark,
    surface = surfaceDark,
    error = errorDark,
    onBackground = onBackgroundDark,
    onSurface = onSurfaceDark,
    onError = onErrorDark,
    onPrimary = onPrimaryDark,
     onSecondary = onSecondaryDark
)

 val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    background = backgroundLight,
    surface = surfaceLight,
    error = errorLight,
    onTertiary = textColorSearchTextLight,
    onBackground = onBackgroundLight,
    onSurface = onSurfaceLight,
    onError = onErrorLight,
    onPrimary = onPrimaryLight,
     onSecondary = onSecondaryLight
)


@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

