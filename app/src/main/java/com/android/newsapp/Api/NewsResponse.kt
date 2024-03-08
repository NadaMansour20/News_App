package com.android.newsapp.Api

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    var articles: List<ArticlesItem>? = null,

    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("massage")
    val massage: String? = null

)


data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: SourcesItem? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
)

