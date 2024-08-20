package com.pbws.domain.repository

import com.pbws.domain.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(source:String,isOnline:Boolean):List<ArticlesItem>
    suspend fun getArticle(id:Int):ArticlesItem
    suspend fun getSearchNews(query:String):List<ArticlesItem>
}