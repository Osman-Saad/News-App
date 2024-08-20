package com.pbws.data.di

import com.pbws.data.repositoryimpl.NewsRepositoryImpl
import com.pbws.data.repositoryimpl.NewsSourcesRepositoryImpl
import com.pbws.domain.repository.NewsRepository
import com.pbws.domain.repository.NewsSourcesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsSourcesRepository(newsSourcesRepositoryImpl: NewsSourcesRepositoryImpl):NewsSourcesRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}