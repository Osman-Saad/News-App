package com.pbws.data.repositoryimpl

import com.pbws.data.constant.fromGson
import com.pbws.data.datasource.SourcesDataSource
import com.pbws.domain.model.SourcesItem
import com.pbws.domain.repository.NewsSourcesRepository
import javax.inject.Inject

class NewsSourcesRepositoryImpl @Inject constructor(private val sourcesDataSource: SourcesDataSource) :NewsSourcesRepository {
    override suspend fun getNewsSources(category: String, isOnline: Boolean): List<SourcesItem> {
        return try {
            sourcesDataSource.getSources(category,isOnline).map {
                it.fromGson(SourcesItem::class.java)
            }
        }catch (ex:Exception){
            throw ex
        }
    }
}