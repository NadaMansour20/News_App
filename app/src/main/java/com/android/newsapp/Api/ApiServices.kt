package com.android.newsapp.Api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    // get headlines sources from api
    @GET("v2/top-headlines/sources")
    suspend fun get_tobheadline_resources(
        @Query("category")
        category: String,
        @Query("apiKey")
        apiKey: String = Constent.api_key
    ): ResourcesResponse


    // get all articles about source
    @GET("v2/everything")
    suspend fun get_all_news_by_source(

        @Query("sources")
        sources: String,
        @Query("apiKey")
        apiKey: String = Constent.api_key,
        @Query("sortBy")
        sortedBy: String = "publishedAt"

    ): NewsResponse

    @GET("v2/everything")
    suspend fun get_all_news_by_search(
        @Query("q")
        q: String,     //used to search for new
        @Query("apiKey")
        apiKey: String = Constent.api_key,
        @Query("sortBy")
        sortedBy: String = "publishedAt"

    ): NewsResponse


}