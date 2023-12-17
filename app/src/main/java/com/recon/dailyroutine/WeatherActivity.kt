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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherActivity : AppCompatActivity() {
    lateinit var testTV: TextView
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var text3: TextView
    lateinit var text4: TextView
    lateinit var text5: TextView
    lateinit var text6: TextView
    lateinit var text7: TextView
    lateinit var text8: TextView
    lateinit var text9: TextView
    lateinit var text10: TextView
    lateinit var text11: TextView
    lateinit var text12: TextView


    val APIKEY = "2392c107c09f422d8f8141306231108"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        init()

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


        val currentDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val targetDate = dateFormat.format(currentDate)
        val targetForecastDay = weatherData.forecast.forecastDay.find { it.date == targetDate }

// Вывод данных почасового прогноза в лог
        targetForecastDay?.let { forecastDay ->
            for (hour in forecastDay.hour) {
                when(hour.time){
                    targetDate.toString() + " 00:00" -> text1.text = "00:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 02:00" -> text2.text = "02:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 04:00" -> text3.text = "04:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 06:00" -> text4.text = "06:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 08:00" -> text5.text = "08:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 10:00" -> text6.text = "10:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 12:00" -> text7.text = "12:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 14:00" -> text8.text = "14:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 16:00" -> text9.text = "16:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 18:00" -> text10.text = "18:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 20:00" -> text11.text = "20:00\n${hour.tempC}°C\n${hour.condition.text}"
                    targetDate.toString() + " 22:00" -> text12.text = "22:00\n${hour.tempC}°C\n${hour.condition.text}"
                }
                Log.e("BIBAA",hour.toString())
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
    private fun init(){
        testTV = findViewById(R.id.tvResponse)
        text1 = findViewById(R.id.tvWeat1)
        text2 = findViewById(R.id.tvWeat2)
        text3 = findViewById(R.id.tvWeat3)
        text4 = findViewById(R.id.tvWeat4)
        text5 = findViewById(R.id.tvWeat5)
        text6 = findViewById(R.id.tvWeat6)
        text7 = findViewById(R.id.tvWeat7)
        text8 = findViewById(R.id.tvWeat8)
        text9 = findViewById(R.id.tvWeat9)
        text10 = findViewById(R.id.tvWeat10)
        text11 = findViewById(R.id.tvWeat11)
        text12 = findViewById(R.id.tvWeat12)
    }
}