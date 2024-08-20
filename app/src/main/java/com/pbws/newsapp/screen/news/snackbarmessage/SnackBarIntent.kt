package com.pbws.newsapp.screen.news.snackbarmessage

sealed class SnackBarIntent {
    data class Error(val message: String) : SnackBarIntent()
}