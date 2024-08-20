package com.pbws.data.datasourceimpl

import android.util.Log
import com.pbws.data.datasource.NewsDataSource
import com.pbws.data.local.dao.NewsDao
import com.pbws.data.model.ArticlesItemDto
import com.pbws.data.remote.NewsApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApiServices: NewsApiServices
) :NewsDataSource {
    override suspend fun getNews(source: String, isOnline: Boolean): List<ArticlesItemDto> {
        try {
            return if(isOnline){
                getNewsOnline(source)
            }else{
                getNewsOffline(source)
            }
        }catch (ex:Exception){
            throw ex
        }
    }

    override suspend fun getArticle(id: Int): ArticlesItemDto {
        try {
            val articlesItemDto = newsDao.getArticle(id)
            return articlesItemDto
        }catch (ex:Exception){
            throw ex
        }
    }

    override suspend fun searchNews(query: String): List<ArticlesItemDto> {
        try {
            return newsApiServices.getSearchNews(query = query).articles?.filterNotNull() ?: emptyList()
        }catch (ex:Exception){
            throw ex
        }
    }

    private suspend fun getNewsOnline(source: String): List<ArticlesItemDto> {
        return try {
            val news = newsApiServices.getNews(sources = source).articles
            cacheNews(source,news?.filterNotNull() ?: emptyList())
            return getNewsOffline(source)
        } catch (ex: Exception) {
            throw ex
        }
    }

    private suspend fun getNewsOffline(source: String):List<ArticlesItemDto>{

        try {
            Log.d("Tag", "getNewsOffline: "+newsDao.getNews(source).size)
            return newsDao.getNews(source)
        }catch (ex:Exception){
            throw ex
        }
    }

    private suspend fun cacheNews(source:String,news:List<ArticlesItemDto>){
        try {
            newsDao.clearAll(source)
            newsDao.insertNews(news)
        } catch (ex:Exception){
            throw ex
        }
    }
}