package com.pbws.domain.usecase

import com.pbws.domain.model.SourcesItem
import com.pbws.domain.repository.NewsSourcesRepository
import javax.inject.Inject

class SourcesUseCase @Inject constructor(
    private val newsSourcesRepository: NewsSourcesRepository
){

    suspend fun getSources(category: String,isOnline:Boolean): List<SourcesItem> {
        return newsSourcesRepository.getNewsSources(category,isOnline)
    }

}