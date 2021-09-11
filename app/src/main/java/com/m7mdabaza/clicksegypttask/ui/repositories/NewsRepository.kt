package com.m7mdabaza.clicksegypttask.ui.repositories

import com.m7mdabaza.clicksegypttask.network.NetworkCallbackResponse

class NewsRepository(networkCallbackResponse: NetworkCallbackResponse) {


    val getNewsData = networkCallbackResponse.getNewsData()

    val newsListMutableLiveData = networkCallbackResponse.newsListMutableLiveData



}