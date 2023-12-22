package com.recon.dailyroutine

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.google.gson.Gson
import com.recon.dailyroutine.AdditionalClasses.NewsResponse
import com.recon.dailyroutine.AdditionalClasses.SharedPrefs
import com.recon.dailyroutine.AdditionalClasses.VolleyRequest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewsActivity : AppCompatActivity() {

    lateinit var title1:TextView
    lateinit var title2:TextView
    lateinit var title3:TextView

    lateinit var description1:TextView
    lateinit var description2:TextView
    lateinit var description3:TextView

    lateinit var image1:ImageView
    lateinit var image2:ImageView
    lateinit var image3:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        fun getOneWeekAgoDate(): String {
            val calendar = Calendar.getInstance()

            // Уменьшаем текущую дату на 7 дней
            calendar.add(Calendar.DAY_OF_YEAR, -7)

            // Получаем дату неделю назад
            val oneWeekAgoDate = calendar.time

            // Форматирование даты в нужный формат (гггг-мм-дд)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return dateFormat.format(oneWeekAgoDate)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        val recyclerView: ConstraintLayout = findViewById(R.id.mainLayout)


        init()
        val savedData = SharedPrefs(this)
        val volleyRequest = VolleyRequest(this)
        val newsTitleList = arrayOf(title1, title2, title3)
        val newsDescList = arrayOf(description1, description2, description3)
        val newsImgList = arrayOf(image1, image2, image3)
        var Urls = arrayOf("","","")
        var refresh = 0
        val token = "5rUgf9YPodT9QbKzA2I6Z5DY1PSXogoiRiJqh8ga"
        val url = "https://api.thenewsapi.com/v1/news/all?" +
                "api_token=$token" +
                "&search=${savedData.loadData("interests","tech").replace(',' , '|')}" +
                "&language=en" +
                "&published_after=${getOneWeekAgoDate()}"

        fun getJson(){
            lifecycleScope.launch {
                try {
                    val response = volleyRequest.getJsonFromUrl(url)
                    val data = response.toString()
                    savedData.saveData("newsJSON", data)
                    println(data)
                } catch (e: Exception) {
                    val errorMessage = e.message ?: "Unknown error"
                    Log.e("MyTag", errorMessage)
                }
            }
        }

        if(savedData.loadData("newsJSON","") == "") {
            getJson()
        }
        fun fillData(){
            val newsResponse = parseJson(savedData.loadData("newsJSON", ""))
            newsResponse?.let { response ->
                var i: Int = 0
                for (newsItem in response.data) {
                    newsTitleList[i].text = newsItem.title
                    newsDescList[i].text = newsItem.description
                    newsImgList[i].load(newsItem.imageUrl) {
                        crossfade(true)
                        placeholder(R.drawable.newspaper)
                    }
                    Urls[i] = newsItem.url
                    println("Title: ${newsItem.title}")
                    println("Description: ${newsItem.description}")
                    println("URL: ${newsItem.url}")
                    println("Published At: ${newsItem.publishedAt}")
                    println("-------------------------")
                    i++
                }
            }
        }
        image1.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(Urls[0]))
        }
        image2.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(Urls[1]))
        }
        image3.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(Urls[2]))
        }
        swipeRefreshLayout.setOnRefreshListener {
            refresh++
            if (refresh % 2 == 0){
                getJson()
                fillData()
            }
            else
            {
                fillData()
            }
            swipeRefreshLayout.isRefreshing = false
        }

    }
    private fun init(){
         title1 = findViewById(R.id.titleTv1)
         title2 = findViewById(R.id.titleTv2)
         title3 = findViewById(R.id.titleTv3)

         description1 = findViewById(R.id.descripTv1)
         description2 = findViewById(R.id.descripTv2)
         description3 = findViewById(R.id.descripTv3)

         image1 = findViewById(R.id.newsImg1)
         image2 = findViewById(R.id.newsImg2)
         image3 = findViewById(R.id.newsImg3)
        Toast.makeText(this,"Swipe for refresh",LENGTH_SHORT).show()
    }
    private fun parseJson(jsonString: String): NewsResponse? {
        return try {
            val gson = Gson()
            gson.fromJson(jsonString, NewsResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}