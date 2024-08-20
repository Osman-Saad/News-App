package com.pbws.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pbws.data.model.ArticlesItemDto
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Query("SELECT * FROM News WHERE id=:source")
    suspend fun getNews(source:String):List<ArticlesItemDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<ArticlesItemDto>)

    @Query("DELETE FROM News WHERE id=:source")
    suspend fun clearAll(source: String)

    @Query("SELECT * FROM News WHERE articleId=:articleId")
    suspend fun getArticle(articleId:Int):ArticlesItemDto

}
