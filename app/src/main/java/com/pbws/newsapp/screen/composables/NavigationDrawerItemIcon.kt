package com.pbws.newsapp.screen.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavigationDrawerItemIcon(icon:Int,iconSize:Int){
    Icon(
        painter = painterResource(id = icon)  ,
        contentDescription = null,
        modifier = Modifier.size(iconSize.dp),
        tint = MaterialTheme.colorScheme.onBackground
    )
}