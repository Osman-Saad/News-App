package com.pbws.newsapp.di

import com.pbws.domain.usecase.ArticleUseCase
import com.pbws.domain.usecase.NewsUseCase
import com.pbws.domain.usecase.SearchNewsUseCase
import com.pbws.domain.usecase.SourcesUseCase
import com.pbws.domain.repository.NewsRepository
import com.pbws.domain.repository.NewsSourcesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideSourcesUseCase(newsSourcesRepository: NewsSourcesRepository): SourcesUseCase {
        return SourcesUseCase(newsSourcesRepository)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase {
        return NewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideNewsDetailsUseCase(newsRepository: NewsRepository): ArticleUseCase {
        return ArticleUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(newsRepository: NewsRepository): SearchNewsUseCase {
        return SearchNewsUseCase(newsRepository)
    }

}