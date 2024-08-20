package com.pbws.newsapp.screen.composables

import OrangeColor
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pbws.newsapp.R
import com.pbws.newsapp.screen.category.CATEGORY_SCREEN_ROUTE
import com.pbws.newsapp.navigation.NewsNavHost
import com.pbws.newsapp.screen.news.snackbarmessage.MessageHandelViewModel
import com.pbws.newsapp.screen.news.snackbarmessage.SnackBarState
import com.pbws.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScaffold(
    navHostController: NavHostController,
    switchChecked: Boolean,
    onThemeSwitchValueChanged: (Boolean) -> Unit,
    themeIsDark: Boolean,
    messageHandelViewModel: MessageHandelViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var messageHandelState = messageHandelViewModel.snackBarState
    var snackBarMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    coroutineScope.launch {
        messageHandelState.collect { state ->
            when (state) {
                is SnackBarState.Error -> snackBarMessage =
                    context.getString(R.string.something_went_wrong)

                SnackBarState.Idle -> {}
                SnackBarState.IsOffline ->{
                    Log.d("TAG", "handelInternetConnected: ${state}")
                    snackBarMessage =
                        context.getString(R.string.no_internet_connection)
                }

                SnackBarState.IsOnline ->{
                    snackBarMessage =
                        context.getString(R.string.internet_connection_restored)
                }
            }
        }
    }


    MainScaffoldContent(
        navHostController = navHostController,
        drawerState = drawerState,
        switchChecked = switchChecked,
        themeIsDark = themeIsDark,
        snackBarMessage = snackBarMessage,
        onCategoryDrawerItemClicked = {
            if (navHostController.currentDestination?.route != CATEGORY_SCREEN_ROUTE) {
                navHostController.popBackStack()
            }
            scope.launch {
                drawerState.close()
            }
        },
        onChangeThemeDrawerItemClicked = {
            scope.launch {
                drawerState.close()
            }
        }) { checked ->
        onThemeSwitchValueChanged(checked)
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScaffoldContent(
    navHostController: NavHostController,
    drawerState: DrawerState,
    switchChecked: Boolean,
    snackBarMessage: String,
    themeIsDark: Boolean,
    onCategoryDrawerItemClicked: () -> Unit,
    onChangeThemeDrawerItemClicked: () -> Unit,
    onThemeSwitchValueChanged: (Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()
    val navigationDrawerItemColor = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = Color.Transparent,
        unselectedContainerColor = Color.Transparent
    )
    val snackBarHostState = remember { SnackbarHostState() }
    if (snackBarMessage.isNotEmpty()) {
        scope.launch {
            Log.d("TAGs", "MainScaffoldContent: $snackBarMessage")
            snackBarHostState.showSnackbar(snackBarMessage)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                DrawerHeader()
                Divider()
                NavigationDrawerItem(
                    colors = navigationDrawerItemColor,
                    modifier = Modifier.padding(vertical = 8.dp),
                    label = {

                        Text(
                            text = stringResource(id = R.string.categories),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                        )
                    },
                    selected = false,
                    onClick = onCategoryDrawerItemClicked,
                    icon = {
                        NavigationDrawerItemIcon(
                            icon = R.drawable.category_ic, iconSize = 18
                        )
                    }
                )
                NavigationDrawerItem(
                    colors = navigationDrawerItemColor,
                    label = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (themeIsDark) stringResource(R.string.light_theme) else stringResource(
                                    R.string.dark_theme
                                ),
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 18.sp,
                            )

                            Switch(
                                checked = switchChecked,
                                onCheckedChange = onThemeSwitchValueChanged,
                                colors = SwitchDefaults.colors(
                                    checkedTrackColor = MaterialTheme.colorScheme.surface,
                                    checkedThumbColor = OrangeColor,
                                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface,
                                    uncheckedTrackColor = MaterialTheme.colorScheme.surface
                                )
                            )
                        }
                    },
                    selected = false,
                    onClick = onChangeThemeDrawerItemClicked,
                    icon = {
                        NavigationDrawerItemIcon(icon = R.drawable.theme_ic, iconSize = 24)
                    }

                )
            }
        }
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                AppBarView(navHostController) {
                    scope.launch {
                        drawerState.open()
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            },
        ) { innerPadding ->
            NewsNavHost(
                navHostController = navHostController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

}
