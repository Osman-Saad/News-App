package com.pbws.newsapp.screen.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.domain.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    var channel: Channel<NewsDetailsIntent>,
    private val articleUseCase: ArticleUseCase
):ViewModel() {
    private val _state = MutableStateFlow<NewsDetailsState>(NewsDetailsState.Ideal)
    val state = _state.asStateFlow()

    init {
        handelIntent()
    }

    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect{intent->
                when (intent) {
                    is NewsDetailsIntent.GetNewsDetails -> handelGetNewsDetails(intent.id)
                }
            }
        }
    }

    private fun handelGetNewsDetails(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _state.value = NewsDetailsState.Loading
                val article = articleUseCase.getArticle(id)
                _state.value = NewsDetailsState.NewsRetrievedSuccess(article)
            }
        }catch (ex:Exception){
            _state.value = NewsDetailsState.Error(ex.localizedMessage)
        }
    }

}