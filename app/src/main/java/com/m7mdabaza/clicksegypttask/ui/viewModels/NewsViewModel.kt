package com.m7mdabaza.clicksegypttask.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.m7mdabaza.clicksegypttask.network.NetworkCallbackResponse
import com.m7mdabaza.clicksegypttask.ui.repositories.NewsRepository


class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val networkCallback = NetworkCallbackResponse()

    private val repository = NewsRepository(networkCallback)

    val getNewsData = repository.getNewsData
    val newsListMutableLiveData = repository.newsListMutableLiveData


}
