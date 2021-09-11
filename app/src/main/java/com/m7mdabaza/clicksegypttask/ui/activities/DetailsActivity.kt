package com.m7mdabaza.clicksegypttask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.m7mdabaza.clicksegypttask.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        getIntentData()

    }


    // receive from Intent
    private fun getIntentData() {
        if (intent.hasExtra("title") && intent.hasExtra("imageURL") && intent.hasExtra("newsDescription")) {
            val title = intent.getStringExtra("title")
            titleDetails.text = title
            val newsDescription = intent.getStringExtra("newsDescription")
            DescriptionDetails.text = newsDescription
            val uImageURL = intent.getStringExtra("imageURL")
            Picasso.get().load(uImageURL).into(newsImageDetails)
        }
    }
}