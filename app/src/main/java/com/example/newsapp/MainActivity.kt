package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity(), Newsitemclicked {
    lateinit var adapter:NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView=findViewById<RecyclerView>(R.id.recview)
        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchData()
        adapter= NewsListAdapter(this)
        recyclerView.adapter=adapter
    }
    private fun fetchData() {
        val url = "https://saurav.tech/NewsAPI/everything/cnn.json"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val newsJsonArray=response.getJSONArray("articles")
                val newsarray=ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsjsonobject=newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsjsonobject.getString("title"),
                        newsjsonobject.getString("url"),
                        newsjsonobject.getString("urlToImage")


                    )
                    newsarray.add(news)
                }
                adapter.updateNews(newsarray)
            },
            { error ->

            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onItemClicked(item: News) {
        val intent = CustomTabsIntent.Builder()
            .build()
        intent.launchUrl(this@MainActivity, Uri.parse(item.url))

    }


}