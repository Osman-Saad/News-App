package com.pbws.newsapp.di

import com.pbws.newsapp.screen.news.NewsScreenIntent
import com.pbws.newsapp.screen.news.snackbarmessage.SnackBarIntent
import com.pbws.newsapp.screen.newsdetails.NewsDetailsIntent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.channels.Channel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChannelModule {
    @Provides
    fun provideNewsIntentScreenChannel():Channel<NewsScreenIntent>{
        return Channel()
    }

    @Provides
    fun provideNewsDetailsIntentScreenChannel():Channel<NewsDetailsIntent>{
        return Channel()
    }
    @Provides
    fun provideSnackBarIntentScreenChannel():Channel<SnackBarIntent>{
        return Channel()
    }

}