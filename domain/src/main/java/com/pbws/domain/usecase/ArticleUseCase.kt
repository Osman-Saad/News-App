package com.pbws.domain.usecase

import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.repository.NewsRepository
import javax.inject.Inject

class ArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun getArticle(id:Int):ArticlesItem{
        try {
            return newsRepository.getArticle(id)
        }catch (ex:Exception){
            throw ex
        }
    }
}