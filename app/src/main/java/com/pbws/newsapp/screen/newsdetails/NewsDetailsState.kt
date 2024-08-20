package com.pbws.newsapp.screen.newsdetails

import com.pbws.domain.model.ArticlesItem

sealed class NewsDetailsState {
    data object Ideal : NewsDetailsState()
    data object Loading : NewsDetailsState()
    data class NewsRetrievedSuccess(val news: ArticlesItem) : NewsDetailsState()
    data class Error(val message: String) : NewsDetailsState()
}