package com.pbws.newsapp.screen.news

import androidx.paging.PagingData
import com.pbws.data.model.ArticlesItemDto
import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.model.SourcesItem
import kotlinx.coroutines.flow.Flow

sealed class NewsScreenState {

    data object Ideal : NewsScreenState()
    data object Loading : NewsScreenState()
    data class NewsRetrievedSuccess(val news: List<ArticlesItem>) : NewsScreenState()
    data class SourcesRetrievedSuccess(val sources: List<SourcesItem>) : NewsScreenState()
    data class SearchedNewsRetrievedSuccess(val news: List<ArticlesItem>) : NewsScreenState()
    data class Error(val message: String) : NewsScreenState()
}