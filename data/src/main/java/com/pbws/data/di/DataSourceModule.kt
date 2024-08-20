package com.pbws.data.di

import com.pbws.data.datasource.NewsDataSource
import com.pbws.data.datasource.SourcesDataSource
import com.pbws.data.datasourceimpl.NewsDataSourceImpl
import com.pbws.data.datasourceimpl.SourcesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
   abstract fun bindSourcesDataSource(sourcesDataSourceImpl: SourcesDataSourceImpl): SourcesDataSource

   @Binds
   abstract fun bindNewsDataSource(newsDataSourceImpl: NewsDataSourceImpl): NewsDataSource
}