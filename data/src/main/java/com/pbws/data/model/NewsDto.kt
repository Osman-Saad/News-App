package com.pbws.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pbws.data.constant.Constant
import com.pbws.domain.model.ArticlesItem
import com.pbws.domain.model.Source
import com.pbws.domain.model.Sources
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
	var articles: List<ArticlesItemDto?>? = null,
	var status: String? = null
)

@Entity(tableName = Constant.NEWS_TABLE_NAME)
@Serializable
data class ArticlesItemDto(
	@Expose(serialize = false, deserialize = false)
	@PrimaryKey(autoGenerate = true)
	var articleId:Int?=null,
	var publishedAt: String? = null,
	var author: String? = null,
	var description: String? = null,
	@Embedded
	var source: SourceDto? = null,
	var title: String? = null,
	var url: String? = null,
	var urlToImage: String? = null,
	var content: String? = null
)

@Serializable
data class SourceDto(
	var name: String? = null,
	var id: String? = null
)
fun ArticlesItemDto.toArticlesItem(): ArticlesItem {
	return ArticlesItem(
		articleId = articleId?:0,
		publishedAt = publishedAt,
		author = author,
		description = description,
		source = Source(name = source?.name, id = source?.id),
		title = title,
		url = url,
		urlToImage = urlToImage,
		content = content
	)
}