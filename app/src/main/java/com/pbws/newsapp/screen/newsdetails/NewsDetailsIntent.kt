package com.pbws.newsapp.screen.newsdetails

sealed class NewsDetailsIntent {
    data class GetNewsDetails(var id:Int): NewsDetailsIntent()
}