package com.recon.dailyroutine

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.recon.dailyroutine.AdditionalClasses.SharedPrefs
import com.recon.dailyroutine.AdditionalClasses.VolleyRequest
import kotlinx.coroutines.launch
import org.json.JSONObject

class WeatherActivity : AppCompatActivity() {
    lateinit var testTV: TextView
    val APIKEY = "2392c107c09f422d8f8141306231108"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)


        //TEST
        testTV = findViewById(R.id.tvResponse)


        //Shared preferences
        val savedData = SharedPrefs(this)
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
        testTV.text = "$locationObj  $currentObj"
    }
}