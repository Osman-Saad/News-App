package com.pbws.newsapp.screen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@Composable
fun TimePublishView(date:String,modifier: Modifier = Modifier){
    Text(
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        text = if(date.isNotEmpty()) convertISO8601ToCustomFormat(date) else "",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.SemiBold)
}

private fun convertISO8601ToCustomFormat(dateStr: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return try {
        val date = inputFormat.parse(dateStr)
        date?.let { outputFormat.format(it) } ?: "Invalid date"
    } catch (e: Exception) {
        "Invalid date format"
    }
}
