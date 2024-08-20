package com.pbws.newsapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.pbws.newsapp.constant.ThemeConfig
import com.pbws.newsapp.screen.composables.MainScaffold
import com.pbws.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : ComponentActivity() {
    @Inject
    lateinit var themeConfig: ThemeConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val intent = intent
            val switchCheckedValue = intent.getBooleanExtra("isDark",false)
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            var darkTheme = isSystemInDarkTheme()
            var switchChecked by remember {
                mutableStateOf(switchCheckedValue) }


            NewsAppTheme(
                darkTheme = if(switchChecked)!darkTheme else darkTheme
            ) {
                val navController = rememberNavController()
                MainScaffold(
                    navHostController = navController,
                    switchChecked =switchChecked ,
                    onThemeSwitchValueChanged = {checked->
                        coroutineScope.launch {
                            themeConfig.saveThemeConfig(checked)
                        }
                        switchChecked = checked
                    },
                    themeIsDark = if(switchChecked)!darkTheme else darkTheme
                )
            }
        }
    }

}








