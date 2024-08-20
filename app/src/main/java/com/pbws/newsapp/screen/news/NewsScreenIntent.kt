package com.pbws.newsapp.screen.news

sealed class NewsScreenIntent {
    data class GetSources(val category: String) : NewsScreenIntent()
    data class GetNews(val source: String) : NewsScreenIntent()
    data class OnSearchQueryChange(val query: String) : NewsScreenIntent()
}