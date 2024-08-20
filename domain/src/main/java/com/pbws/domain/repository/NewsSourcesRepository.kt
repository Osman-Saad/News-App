package com.pbws.domain.repository

import com.pbws.domain.model.SourcesItem

interface NewsSourcesRepository {
    suspend fun getNewsSources(category: String, isOnline: Boolean):List<SourcesItem>
}