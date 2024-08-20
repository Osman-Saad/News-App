package com.pbws.newsapp.di

import android.content.Context
import com.pbws.newsapp.constant.ThemeConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ObjectsModule {

    @Provides
    @Singleton
    fun provideThemeConfig(@ApplicationContext context: Context): ThemeConfig {
        return ThemeConfig(context)
    }
}