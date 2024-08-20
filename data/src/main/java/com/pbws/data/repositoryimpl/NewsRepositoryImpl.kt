package com.pbws.data.repositoryimpl

import com.pbws.data.constant.fromGson
import com.pbws.data.datasource.NewsDataSource
import com.pbws.data.model.toArticlesItem
import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) :NewsRepository {
    override suspend fun getNews(source: String, isOnline: Boolean): List<ArticlesItem> {
        return try {
            newsDataSource.getNews(source, isOnline).map{
                it.toArticlesItem()
            }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getArticle(id: Int): ArticlesItem {
        try {
            return newsDataSource.getArticle(id).toArticlesItem()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getSearchNews(query: String): List<ArticlesItem> {
        try {
            return newsDataSource.searchNews(query).mapIndexed { index, it ->
                it.copy(articleId = index ).toArticlesItem()
            }
        }catch (ex:Exception){
            throw ex
        }
    }
}
