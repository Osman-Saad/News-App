package com.pbws.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pbws.data.local.dao.NewsDao
import com.pbws.data.local.dao.SourcesDao
import com.pbws.data.model.ArticlesItemDto
import com.pbws.data.model.SourcesItemDto

@Database(entities = [SourcesItemDto::class, ArticlesItemDto::class] ,version = 1)
abstract class NewsDataBase:RoomDatabase() {
    abstract fun getSourcesDao():SourcesDao
    abstract fun getNewsDao(): NewsDao
}
