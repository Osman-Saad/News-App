package com.pbws.domain.usecase

import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun searchNews(query: String): List<ArticlesItem> {
        try {
            return newsRepository.getSearchNews(query)
        }catch (ex:Exception){
            throw ex
        }
    }
}