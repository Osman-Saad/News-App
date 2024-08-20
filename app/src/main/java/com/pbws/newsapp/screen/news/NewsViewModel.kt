package com.pbws.newsapp.screen.news

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecase.NewsUseCase
import com.pbws.domain.usecase.SearchNewsUseCase
import com.pbws.domain.usecase.SourcesUseCase
import com.pbws.newsapp.util.NetworkMonitor
import com.pbws.newsapp.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var channel: Channel<NewsScreenIntent>,
    private val sourcesUseCase: SourcesUseCase,
    private val networkMonitor: NetworkMonitor,
    private val savedStateHandle: SavedStateHandle,
    private val newsUseCase: NewsUseCase,
    private val searchUseCase: SearchNewsUseCase
) : ViewModel() {

    private val isOnline = networkMonitor.checkForInternet()
    var state = SingleLiveEvent<NewsScreenState>()

    init {
        handleIntent()
    }

    private val args = NewsScreenArgs(savedStateHandle)
    fun getCategory(): String {
        return args.name
    }

    private fun handleIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is NewsScreenIntent.GetSources -> handelGetSources(intent.category)
                    is NewsScreenIntent.GetNews -> handelGetNews(intent.source)
                    is NewsScreenIntent.OnSearchQueryChange -> handelOnSearchQueryChange(intent.query)
                }
            }
        }
    }

    private fun handelOnSearchQueryChange(query: String) {
        try {
            state.postValue(NewsScreenState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                networkMonitor.isConnected.collect{
                    if(it == true){
                    val news = searchUseCase.searchNews(query)
                    state.postValue(NewsScreenState.SearchedNewsRetrievedSuccess(news))
                }
                }
            }
        }catch (es:Exception){
            state.postValue(NewsScreenState.Error(es.localizedMessage))
        }
    }

    private fun handelGetNews(source: String) {
        try {
            state.postValue(NewsScreenState.Loading)
            viewModelScope.launch {
                networkMonitor.isConnected.collect{
                    val news = newsUseCase.getNews(source, it?:isOnline)
                    state.postValue(NewsScreenState.NewsRetrievedSuccess(news))
                }
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    private fun handelGetSources(category: String) {
        try {
            state.postValue(NewsScreenState.Loading)
            viewModelScope.launch(Dispatchers.IO) {
                val sources = sourcesUseCase.getSources(category, isOnline)
                Log.d("TAG", "handelGetSources: $sources")
                state.postValue(NewsScreenState.SourcesRetrievedSuccess(sources))
            }
        } catch (ex: Exception) {
            state.postValue(NewsScreenState.Error(ex.localizedMessage))

        }
    }
}