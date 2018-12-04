package com.example.kehtolaulu.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Response

class ForecastActivity : AppCompatActivity(), Callback {
    override fun onSuccess(response: Response<CitiesForecast>?) {

    }

    override fun callback(position: Int) {
        this.position = position
    }

    var api: WeatherApi.ApiInterface? = WeatherApi.client?.create(WeatherApi.ApiInterface::class.java)
    private lateinit var tvCity: TextView
    private lateinit var tvCountry: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvWind: TextView
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        tvCity = findViewById(R.id.tv_city)
        tvCountry = findViewById(R.id.tv_country)
        tvTemp = findViewById(R.id.tv_temp)
        tvHumidity = findViewById(R.id.tv_humidity)
        tvPressure = findViewById(R.id.tv_pressure)
        tvWind = findViewById(R.id.tv_wind)
        position = intent.getIntExtra("position", 0)
        setWeather(position)
    }

    private fun setWeather(position: Int) {
        tvCity.text = Weather.data?.get(position)?.name
        tvCountry.text = Weather.data?.get(position)?.sys?.country
        tvTemp.text = Weather.data?.get(position)?.main?.temp.toString() + "˚C"
        tvHumidity.text = Weather.data?.get(position)?.main?.humidity.toString()
        tvPressure.text = Weather.data?.get(position)?.main?.pressure.toString()

        var wind = Weather.data?.get(position)?.wind?.deg
        /*
        337-360 0-22 - north
        22-67 - north east
        67-112 east
        112-157 - south east
        157-202 south
        202-247 south west
        247-292 west
        292-337 north west
         */
        if (wind != null) {
            tvWind.text = degToString(wind)
        }
    }

    private fun degToString(wind: Double): String {
        var str = when (wind) {
            in 315.0..360.0 -> "north"
            in 0.0..45.0 -> "north"
            in 135.0..225.0 -> "south"
            else -> ""
        }
        if (wind in 45.0..135.0) {
            str += " east"
        } else if (wind in 225.0..315.0) {
            str += " west"
        }
        return str.trim()
    }

}
