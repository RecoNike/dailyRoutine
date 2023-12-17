import com.google.gson.annotations.SerializedName

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Long,
    val localtime: String
)

data class Condition(
    val text: String,
    val icon: String
)

data class Current(
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("is_day") val isDay: Int,
    val condition: Condition,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double
)

data class Day(
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("totalsnow_cm") val totalSnowCm: Double,
    @SerializedName("daily_will_it_rain") val dailyWillItRain: Int,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int,
    @SerializedName("daily_will_it_snow") val dailyWillItSnow: Int,
    @SerializedName("daily_chance_of_snow") val dailyChanceOfSnow: Int,
    val condition: Condition
)

data class Astro(
    val sunrise: String,
    val sunset: String
)

data class Hour(
    val time: String,
    @SerializedName("temp_c") val tempC: Double,
    val condition: Condition,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("chance_of_rain") val chanceOfRain: Int,
    @SerializedName("chance_of_snow") val chanceOfSnow: Int
)

data class ForecastDay(
    val date: String,
    @SerializedName("date_epoch") val dateEpoch: Long,
    val day: Day,
    val astro: Astro,
    val hour: List<Hour>
)

data class Forecast(
    @SerializedName("forecastday") val forecastDay: List<ForecastDay>
)

data class WeatherData(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)
