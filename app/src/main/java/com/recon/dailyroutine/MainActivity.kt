package com.recon.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.recon.dailyroutine.AdditionalClasses.SharedPrefs
import com.recon.dailyroutine.AdditionalClasses.VolleyRequest
import com.recon.dailyroutine.DataBase.AppDatabase
import com.recon.dailyroutine.DataBase.MoodEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var tvCity: TextView
    lateinit var tvCurrentTemp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moodEntry = MoodEntry(7, "22.12.2023","sea")
        val moodDao = AppDatabase.getDatabase(this@MainActivity).moodDao()

        lifecycleScope.launch(Dispatchers.Main) {
            moodDao.insertMoodEntry(moodEntry)
        }
        lifecycleScope.launch(Dispatchers.Main) {
            val res = moodDao.getMoodEntriesForDate("22.12.2023")
            println(res)
        }
        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        tvCity = findViewById(R.id.TVCity)
        tvCurrentTemp = findViewById(R.id.TVCurrentTemp)
        val savedData = SharedPrefs(this)
        val APIKEY = "2392c107c09f422d8f8141306231108"


        if (savedData.loadData("init","false") != "true") {
            val i: Intent = Intent(this, SettingActivity::class.java)
            startActivity(i)
        }

        //WEATHER Button
        val weatherButton: Button = findViewById(R.id.weatherBtn)
        weatherButton.setOnClickListener {
            val weather: Intent = Intent(this, WeatherActivity::class.java)
            startActivity(weather)
        }

        //NEWS Button
        val newsButton: Button = findViewById(R.id.newsBtn)
        newsButton.setOnClickListener {
            val news: Intent = Intent(this, NewsActivity::class.java)
            startActivity(news)
        }
        //BRAIN button
        val brainButton: Button = findViewById(R.id.startBrainBtn)
        brainButton.setOnClickListener {
            val brain: Intent = Intent(this, StartBrainActivity::class.java)
            startActivity(brain)
        }

        //SETTINGS Button
        val settingsBtn: ImageView = findViewById(R.id.settingsImg)
        settingsBtn.setOnClickListener {
            val set: Intent = Intent(this, SettingActivity::class.java)
            startActivity(set)
        }

        //Meditation Button
        val meditatBtn: Button = findViewById(R.id.meditationBtn)
        meditatBtn.setOnClickListener {
            val med: Intent = Intent(this, MeditationActivity::class.java)
            startActivity(med)
        }

        //Mood Button
        val moodBtn: Button = findViewById(R.id.moodBtn)
        moodBtn.setOnClickListener {
            val mood: Intent = Intent(this, MoodTrackActivity::class.java)
            startActivity(mood)
        }


            val volleyRequest = VolleyRequest(this)
            val url = "https://api.weatherapi.com/v1/current.json?" +
                    "key=$APIKEY" +
                    "&q=${savedData.loadData("city","London")}" +
                    "&aqi=no"
            // Используем корутину для выполнения запроса в фоновом потоке
            lifecycleScope.launch {
                try {
                    val response = volleyRequest.getJsonFromUrl(url)
                    // Обрабатываем успешный ответ
                    val data = response.toString()
                    parseJSONY(data)
                    Log.d("MyTag",data)
                    // Делайте что-то с данными, например, обновляйте UI
                } catch (e: Exception) {
                    // Обрабатываем ошибку
                    val errorMessage = e.message ?: "Unknown error"
                    // Делайте что-то с сообщением об ошибке, например, выводите его
                    Log.d("MyTag",errorMessage)
                }
            }
    }
    private fun parseJSONY(JSN: String){
        val obj = JSONObject(JSN)
        var locationObj = obj.getJSONObject("location").getString("name")
        var currentObj = obj.getJSONObject("current").getString("temp_c") + " C°"
        tvCity.text = locationObj
        tvCurrentTemp.text = currentObj
    }
}