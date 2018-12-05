package com.example.kehtolaulu.weatherapp

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response


object Weather {

    var data: List<CitiesForecast.City>? = null

    var api: WeatherApi.ApiInterface? = WeatherApi.client?.create(WeatherApi.ApiInterface::class.java)

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
                Toast.makeText(context, "Fail sore", Toast.LENGTH_LONG)
            }
        })
    }

}
