package com.m7mdabaza.clicksegypttask.pojo.networkResponseObjects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,

    @SerializedName("articles")
    @Expose
    val articles: ArrayList<Article>
)
