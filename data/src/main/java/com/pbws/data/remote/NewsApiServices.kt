package com.pbws.data.remote

import com.pbws.data.BuildConfig
import com.pbws.data.constant.Constant
import com.pbws.data.model.NewsDto
import com.pbws.data.model.SourcesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiServices {
    @GET(Constant.SOURCES_ENDPOINT)
    suspend fun getSources(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("category") category: String,
    ):SourcesDto

    @GET(Constant.NEWS_ENDPOINT)
    suspend fun getNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("sources") sources: String,
    ): NewsDto

    @GET(Constant.NEWS_ENDPOINT)
    suspend fun getSearchNews(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query:String
    ): NewsDto


}
