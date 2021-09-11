package com.m7mdabaza.clicksegypttask.network.retrofit


import com.m7mdabaza.clicksegypttask.pojo.networkResponseObjects.NewsData
import retrofit2.Call
import retrofit2.http.GET

interface ApiDataInterface {

    @GET("top-headlines?country=eg&apiKey=63b1f94dad044add871d1e319c630265")
    fun getNews(): Call<NewsData>
}