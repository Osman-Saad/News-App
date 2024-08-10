package com.pbws.newsapp.news.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TimePublishView(modifier: Modifier = Modifier){
    Text(
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        text = "7 hours ago",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.SemiBold)
}