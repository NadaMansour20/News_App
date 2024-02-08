package com.android.newsapp.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("v2/top-headlines/sources")
    fun get_category_resources(

        @Query("apiKey")
        apiKey:String,

        @Query("category")
        category:String

    ):Call<ResourcesResponse>


    @GET("v2/everything")
    fun get_all_news(

        @Query("apiKey")
        apiKey:String,

    ):Call<NewsResponse>


}