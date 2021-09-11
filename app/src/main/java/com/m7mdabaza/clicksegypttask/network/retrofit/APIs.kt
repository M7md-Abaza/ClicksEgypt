package com.m7mdabaza.clicksegypttask.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIs {

    //https://newsapi.org/v2/top-headlines?country=eg&apiKey=63b1f94dad044add871d1e319c630265


    private const val BASE_URL = "https://newsapi.org/v2/"


    private fun getApi(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val DATA_OBJECT : ApiDataInterface = getApi().create(ApiDataInterface::class.java)
}