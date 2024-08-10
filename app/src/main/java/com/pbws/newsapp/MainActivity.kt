package com.pbws.newsapp

import OrangeColor
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pbws.newsapp.category.CategoryScreen
import com.pbws.newsapp.news.composables.AppBarView
import com.pbws.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val navHostController = rememberNavController()
                MainScaffold(navHostController)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(navHostController: NavHostController){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var switchChecked by remember { mutableStateOf(false) }

    val navigationDrawerItemColor = NavigationDrawerItemDefaults.colors(
        selectedContainerColor = Color.Transparent,
        unselectedContainerColor = Color.Transparent
    )

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
                            text = "Categories",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                            )
                    },
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        NavigationDrawerItemIcon(
                            icon = R.drawable.category_ic, iconSize = 18 )
                    }
                )
                NavigationDrawerItem(
                    colors = navigationDrawerItemColor,
                    label = {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = "Dark Mode",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 18.sp,
                            )

                            Switch(
                                checked = switchChecked ,
                                onCheckedChange ={
                                    switchChecked = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedTrackColor = MaterialTheme.colorScheme.surface,
                                    checkedThumbColor = OrangeColor,
                                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface,
                                    uncheckedTrackColor = MaterialTheme.colorScheme.surface
                                )
                            )
                        }
                    },
                    selected = false ,
                    onClick = { /*TODO*/ },
                    icon = {
                        NavigationDrawerItemIcon(icon = R.drawable.theme_ic, iconSize = 24 )
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
            }
        ) {innerPadding ->
                NewsNavHost(
                    navHostController = navHostController,
                    modifier = Modifier.padding(innerPadding))
        }
    }

}



@Composable
fun DrawerHeader(){
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.news_aap_logo) ,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            )
            Text(
                text = "News App",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = OrangeColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun NavigationDrawerItemIcon(icon:Int,iconSize:Int){
    Icon(
        painter = painterResource(id = icon)  ,
        contentDescription = null,
        modifier = Modifier.size(iconSize.dp),
        tint = MaterialTheme.colorScheme.onBackground
    )
}

@Preview(showSystemUi = true , uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        MainScaffold(rememberNavController())
    }}