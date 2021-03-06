package com.m7mdabaza.clicksegypttask.pojo.networkResponseObjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source")
    @Expose
    val source: Source,
    @SerializedName("author")
    @Expose
    val author: Any,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: Any,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("urlToImage")
    @Expose
    val urlToImage: Any,

    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String,

    @SerializedName("content")
    @Expose
    private val content: Any
)
