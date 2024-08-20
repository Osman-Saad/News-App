package com.pbws.data.datasource

import com.pbws.data.model.SourcesItemDto

interface SourcesDataSource {
    suspend fun getSources(category: String, isOnline: Boolean,): List<SourcesItemDto>
}