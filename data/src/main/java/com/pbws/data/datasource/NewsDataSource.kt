package com.pbws.data.datasource

import com.pbws.data.model.ArticlesItemDto
import com.pbws.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {
    suspend fun getNews(source:String,isOnline:Boolean):List<ArticlesItemDto>
    suspend fun getArticle(id:Int): ArticlesItemDto
    suspend fun searchNews(query:String):List<ArticlesItemDto>

}