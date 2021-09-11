package com.m7mdabaza.clicksegypttask.network

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.m7mdabaza.clicksegypttask.network.retrofit.APIs
import com.m7mdabaza.clicksegypttask.pojo.networkResponseObjects.NewsData
import com.m7mdabaza.clicksegypttask.pojo.recyclerModels.NewsRecyclerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class NetworkCallbackResponse {
    val newsList = ArrayList<NewsRecyclerModel>()


    var newsListMutableLiveData: MutableLiveData<ArrayList<NewsRecyclerModel>> =
        MutableLiveData<ArrayList<NewsRecyclerModel>>()


    fun getNewsData() {
        APIs.DATA_OBJECT.getNews().enqueue(object : Callback<NewsData> {
            override fun onResponse(
                call: Call<NewsData>,
                response: Response<NewsData>
            ) {
                if (response.isSuccessful) {
                    var i = 0
                    while (i < response.body()!!.articles.size) {

                        newsList.add(
                            NewsRecyclerModel(
                                response.body()!!.articles[i].title + "",
                                response.body()!!.articles[i].source.name + "",
                                response.body()!!.articles[i].urlToImage as String + "",
                                response.body()!!.articles[i].description as String + "",
                            )
                        )
                        Log.d(TAG, "MAA onResponse: " + response.body()!!.articles[i].title)
                        i++
                    }

                    newsListMutableLiveData.value = newsList

                }

                    Log.d(TAG, "getNewsData:" + newsList.size)

            }

            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                Log.d(TAG, "MAA onFailure : ${t.localizedMessage}")

            }

        })

    }


}