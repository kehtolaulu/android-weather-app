package com.example.kehtolaulu.weatherapp

import retrofit2.Response

interface Callback {
    fun callback(position: Int)
    fun onSuccess(response: Response<CitiesForecast>?)
}
