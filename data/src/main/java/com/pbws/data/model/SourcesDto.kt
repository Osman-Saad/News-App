package com.pbws.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
@Serializable
data class SourcesDto(

	@field:SerializedName("sources")
	val sources: List<SourcesItemDto?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

@Serializable
@Entity(tableName = "sources")
data class SourcesItemDto(

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("category")
	val category: String? = null,
)
