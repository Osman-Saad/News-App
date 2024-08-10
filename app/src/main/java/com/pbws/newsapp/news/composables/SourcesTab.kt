package com.pbws.newsapp.news.composables

import OrangeColor
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SourcesTap(sources:List<String>,onSourceSelected:(sourceId:String)->Unit) {
    var tabSelectedIndex by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(tabSelectedIndex) {
        onSourceSelected(sources[tabSelectedIndex])
    }
    ScrollableTabRow(
        selectedTabIndex = tabSelectedIndex,
        containerColor = Color.Transparent,
        indicator = {},
        divider = {},
        edgePadding =16.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        sources.forEachIndexed{index, item ->
            Tab(
                selected = tabSelectedIndex==index,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .indication(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(radius = 18.dp, color = Color.Transparent)
                    ),
                onClick = {
                    tabSelectedIndex = index
                }) {
                Text(
                    text = item,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (tabSelectedIndex == index) Color.White
                    else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            color = if (tabSelectedIndex == index) OrangeColor else MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(vertical = 8.dp, horizontal = 18.dp)
                )
            }
        }

    }
}