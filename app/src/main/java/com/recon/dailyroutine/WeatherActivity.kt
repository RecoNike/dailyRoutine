package com.recon.dailyroutine

import WeatherData
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
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
        val url = "https://api.weatherapi.com/v1/forecast.json?" +
                "key=$APIKEY" +
                "&q=${savedData.loadData("city","London")}" +
                "&days=2" +
                "&aqi=no&alerts=no"

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
//        var locationObj = obj.getJSONObject("location").getString("name")
//        var currentObj = obj.getJSONObject("current").getString("temp_c") + " C°"
//            testTV.text = "$locationObj  $currentObj"
        val jsonString = JSN
        val gson = Gson()
        val weatherData = gson.fromJson(jsonString, WeatherData::class.java)
        Log.d("WeatherInfo", "Location: ${weatherData.location.name}, ${weatherData.location.country}")
        Log.d("WeatherInfo", "Temperature: ${weatherData.current.tempC}°C")
        Log.d("WeatherInfo", "Condition: ${weatherData.current.condition.text}")
        Log.d("WeatherInfo", "Wind: ${weatherData.current.windMph} mph, ${weatherData.current.windDir}")
        Log.d("WeatherInfo", "Humidity: ${weatherData.current.humidity}%")
        Log.d("WeatherInfo", "Cloud: ${weatherData.current.cloud}%")
        Log.d("WeatherInfo", "Feels Like: ${weatherData.current.feelsLikeC}°C")


        val targetDate = "2023-12-18" // Замените на актуальную дату
        val targetForecastDay = weatherData.forecast.forecastDay.find { it.date == targetDate }

// Вывод данных почасового прогноза в лог
        targetForecastDay?.let { forecastDay ->
            for (hour in forecastDay.hour) {
                Log.i("HourlyForecast", "Time: ${hour.time}")
                Log.i("HourlyForecast", "Temperature: ${hour.tempC}°C")
                Log.d("HourlyForecast", "Condition: ${hour.condition.text}")
                Log.d("HourlyForecast", "Feels Like: ${hour.feelsLikeC}°C")
                Log.d("HourlyForecast", "Chance of Rain: ${hour.chanceOfRain}%")
                Log.d("HourlyForecast", "Chance of Snow: ${hour.chanceOfSnow}%")
                Log.e("HourlyForecast", "-------------------------")
            }
        } ?: Log.d("HourlyForecast", "No forecast data available for $targetDate")
    }
}