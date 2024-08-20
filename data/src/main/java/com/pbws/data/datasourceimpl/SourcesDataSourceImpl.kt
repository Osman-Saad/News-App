package com.pbws.data.datasourceimpl

import android.util.Log
import com.pbws.data.datasource.SourcesDataSource
import com.pbws.data.local.dao.SourcesDao
import com.pbws.data.model.SourcesItemDto
import com.pbws.data.remote.NewsApiServices
import javax.inject.Inject


class SourcesDataSourceImpl @Inject constructor(
    private val sourcesApiService: NewsApiServices,
    private val sourcesDao: SourcesDao)
    :SourcesDataSource {
    override suspend fun getSources(category: String, isOnline: Boolean): List<SourcesItemDto> {
        try {
            if(isOnline){
                cacheSources(category)
                val sources = sourcesApiService.getSources(category = category).sources
                return checkNotNull(sources?.map { checkNotNull(it) })
            }else{
                return sourcesDao.getAllSources(category)
            }

        }catch (ex:Exception){
            throw ex
        }
    }

    private suspend fun cacheSources(category: String){
        try {
            val response = sourcesApiService.getSources(
                category = category
            )
            if(response.status == "ok"){
                sourcesDao.deleteSources(category)
                val sources = response.sources
                sourcesDao.insertSources(checkNotNull(sources?.map { checkNotNull(it) }))
            }
        }catch (ex:Exception){
            throw ex
        }
    }
}