package com.example.kehtolaulu.weatherapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kehtolaulu.weatherapp.*
import com.example.kehtolaulu.weatherapp.interfaces.PositionCallback
import com.example.kehtolaulu.weatherapp.network.Weather
import com.example.kehtolaulu.weatherapp.network.WeatherApi
import kotlinx.android.synthetic.main.activity_forecast.*

class ForecastActivity : AppCompatActivity(), PositionCallback {

    override fun callback(position: Int) {
        this.position = position
    }

    var api: WeatherApi.ApiInterface? = WeatherApi.buildRetrofit().create(WeatherApi.ApiInterface::class.java)
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        position = intent.getIntExtra("position", 0)
        setWeather(position)
    }

    private fun setWeather(position: Int) {
        tvCity.text = Weather.data?.get(position)?.name
        tvCountry.text = Weather.data?.get(position)?.sys?.country
        tvTemp.text = getString(R.string.tv_temp, Weather.data?.get(position)?.main?.temp.toString())
        tvHumidity.text = getString(R.string.tv_humidity, Weather.data?.get(position)?.main?.humidity.toString())
        tvPressure.text = getString(R.string.tv_pressure, Weather.data?.get(position)?.main?.pressure.toString())
        var wind = Weather.data?.get(position)?.wind?.deg
        if (wind != null) {
            tvWind.text = getString(R.string.tv_wind, degToString(wind))
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
