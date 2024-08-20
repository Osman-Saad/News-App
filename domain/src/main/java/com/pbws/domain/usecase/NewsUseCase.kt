package com.pbws.domain.usecase

import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.repository.NewsRepository
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun getNews(source:String,isOnline:Boolean):List<ArticlesItem>{
       return try {
           newsRepository.getNews(source,isOnline)
       }catch (ex:Exception){
           throw ex
       }
    }

}