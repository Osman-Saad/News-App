package com.pbws.newsapp.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.pbws.newsapp.util.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkMonitorModule {
    @RequiresApi(Build.VERSION_CODES.N)
    @Provides
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitor(context)
    }
}