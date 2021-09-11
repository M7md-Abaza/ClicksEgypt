package com.m7mdabaza.clicksegypttask.ui.activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.m7mdabaza.clicksegypttask.adapters.NewsRecyclerAdapter
import com.m7mdabaza.clicksegypttask.ui.viewModels.NewsViewModel
import com.m7mdabaza.clicksegypttask.R
import com.m7mdabaza.clicksegypttask.pojo.recyclerModels.NewsRecyclerModel
import com.m7mdabaza.clicksegypttask.network.CheckInternetConnection
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsViewModel
    private var newsRecyclerAdapter = NewsRecyclerAdapter()

    private var displayNewsList = ArrayList<NewsRecyclerModel>()
    private var receiveNewsList = ArrayList<NewsRecyclerModel>()

    private var checkInternetConnection = CheckInternetConnection()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        progressLayout.visibility = View.VISIBLE

        val isConnected = checkInternetConnection.isConnected(this)
        if (isConnected) {
            viewModel = ViewModelProvider(this)[NewsViewModel(application)::class.java]
            getDataFromViewModel()
            displayNewsList.addAll(receiveNewsList)

        } else if (!isConnected) {
            progress_circular.visibility = View.GONE
            noInternet.visibility = View.VISIBLE
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        val item: MenuItem = menu!!.findItem(R.id.action_search)

        val searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    displayNewsList.clear()
                    val searchText = newText.lowercase(Locale.getDefault())
                    for (data in receiveNewsList) {
                        if (data.title.lowercase(Locale.getDefault())
                                .contains("" + searchText + "")
                        ) {
                            displayNewsList.add(data)
                        }
                        displayRecyclerData(displayNewsList)
                    }
                } else {
                    displayNewsList.clear()
                    displayNewsList.addAll(receiveNewsList)
                    displayRecyclerData(displayNewsList)
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getDataFromViewModel() {
        viewModel.getNewsData
        viewModel.newsListMutableLiveData.observe(this) { recycleHomeModels ->
            receiveNewsList.addAll(recycleHomeModels)
            progressLayout.visibility = View.GONE
            displayRecyclerData(receiveNewsList)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun displayRecyclerData(NewsList: ArrayList<NewsRecyclerModel>) {
        newsRecycler.layoutManager = LinearLayoutManager(this)
        newsRecyclerAdapter.setList(NewsList, this@HomeActivity)
        newsRecycler.adapter = newsRecyclerAdapter
        newsRecyclerAdapter.notifyDataSetChanged()
    }

}