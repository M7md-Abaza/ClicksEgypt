package com.m7mdabaza.clicksegypttask.adapters

import android.util.Pair as UtilPair
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.m7mdabaza.clicksegypttask.pojo.recyclerModels.NewsRecyclerModel
import com.m7mdabaza.clicksegypttask.R
import com.m7mdabaza.clicksegypttask.ui.activities.DetailsActivity
import com.m7mdabaza.clicksegypttask.ui.activities.HomeActivity
import com.squareup.picasso.Picasso
import java.util.*

class NewsRecyclerAdapter :
    RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    private var newsModelList = ArrayList<NewsRecyclerModel>()
    private lateinit var mContext: HomeActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_card_item, parent, false)
        return NewsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {


        val newsModel = newsModelList[position]

        holder.title.text = newsModel.title
        holder.sourceName.text = "Via: " + newsModel.sourceName

        val imageURL: String = newsModel.imageUrl
        Picasso.get().load(imageURL).into(holder.newsImage)

        holder.itemView.setOnClickListener {

            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("title", newsModel.title)
            intent.putExtra("newsDescription", newsModel.description)
            intent.putExtra("imageURL", newsModel.imageUrl)

            val options = ActivityOptions.makeSceneTransitionAnimation(
                mContext,
                UtilPair.create(holder.title, "title"),
                UtilPair.create(holder.newsImage, "newsImage")
            )

            mContext.startActivity(intent, options.toBundle())
        }

    }

    override fun getItemCount(): Int = newsModelList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsImage: ImageView = itemView.findViewById(R.id.newsImage_item)
        var title: TextView = itemView.findViewById(R.id.title_item)
        var sourceName: TextView = itemView.findViewById(R.id.sourceName_item)

    }

    fun setList(storeModelList: ArrayList<NewsRecyclerModel>, mContext: HomeActivity) {
        this.newsModelList = storeModelList
        this.mContext = mContext

    }

}