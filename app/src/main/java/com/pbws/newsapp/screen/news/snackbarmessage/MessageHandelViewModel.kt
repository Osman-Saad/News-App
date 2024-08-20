package com.pbws.newsapp.screen.news.snackbarmessage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pbws.newsapp.util.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageHandelViewModel @Inject constructor(
     val channel: Channel<SnackBarIntent>,
    private val networkMonitor: NetworkMonitor
):ViewModel() {

    private var hasBeenOffline = false
    private var _snackBarState: MutableStateFlow<SnackBarState> = MutableStateFlow(SnackBarState.Idle)
    val snackBarState:Flow<SnackBarState> = _snackBarState

    init {
        handelIntent()
        handelInternetConnected()
    }

    private fun handelIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect{intent->
                when(intent){
                    is SnackBarIntent.Error->handelError(intent.message)
                }

            }
        }
    }

    private fun handelInternetConnected() {
        viewModelScope.launch {
            networkMonitor.isConnected.collectLatest { connected ->
                    if(connected){
                        if(hasBeenOffline){
                            _snackBarState.value = SnackBarState.IsOnline
                        }
                    }else{
                        Log.d("TAGs", "handelInternetConnected: ${connected}")
                        _snackBarState.value = SnackBarState.IsOffline
                        hasBeenOffline = true
                    }
            }
        }
    }

    private fun handelError(message: String) {
        _snackBarState.value = SnackBarState.Error(message)
    }
}