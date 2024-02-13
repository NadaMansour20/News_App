package com.android.newsapp.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    // get headlines sources from api
    @GET("v2/top-headlines/sources")
    fun get_resources(

        @Query("apiKey")
        apiKey: String,

        ): Call<ResourcesResponse>


    // get all articles about source
    @GET("v2/everything")
    fun get_all_news(

        @Query("apiKey")
        apiKey: String,
        @Query("sources")
        sources: String

    ):Call<NewsResponse>


}