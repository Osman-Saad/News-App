package com.pbws.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pbws.data.model.SourcesItemDto

@Dao
interface SourcesDao {

    @Query("SELECT * FROM sources WHERE category = :category")
    suspend fun getAllSources(category: String): List<SourcesItemDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(sources:List<SourcesItemDto>)

    @Query("DELETE FROM sources WHERE category = :category")
    suspend fun deleteSources(category: String)

}