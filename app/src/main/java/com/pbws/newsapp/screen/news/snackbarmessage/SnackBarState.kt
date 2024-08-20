package com.pbws.newsapp.screen.news.snackbarmessage

sealed class SnackBarState {
    data object Idle : SnackBarState()
    data class Error(val message: String) : SnackBarState()
    data object IsOnline:SnackBarState()
    data object IsOffline:SnackBarState()

}