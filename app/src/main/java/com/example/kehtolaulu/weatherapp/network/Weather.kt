package com.example.kehtolaulu.weatherapp.network

import android.content.Context
import com.example.kehtolaulu.weatherapp.database.AppDatabase
import com.example.kehtolaulu.weatherapp.entities.CitiesForecast
import com.example.kehtolaulu.weatherapp.interfaces.Callback
import retrofit2.Call
import retrofit2.Response


object Weather {
    private var db: AppDatabase? = null
    var data: List<CitiesForecast.City>? = null

    var api: WeatherApi.ApiInterface? = WeatherApi.buildRetrofit().create(WeatherApi.ApiInterface::class.java)

    private const val units = "metric"
    private const val cnt = 20
    private val key = WeatherApi.KEY

    fun getWeather(lat: Double?, lng: Double?, callback: Callback, context: Context) {
        var callCitiesForecast = api?.getCitiesForecast(lat, lng, units, cnt, key)
        callCitiesForecast?.enqueue(object : retrofit2.Callback<CitiesForecast> {
            override fun onResponse(call: Call<CitiesForecast>?, response: Response<CitiesForecast>?) {
                data = response?.body()?.list
                callback.onSuccess(response)
            }

            override fun onFailure(call: Call<CitiesForecast>?, t: Throwable?) {
                data = db?.weatherDao()?.getAll()
            }
        })
    }

}
