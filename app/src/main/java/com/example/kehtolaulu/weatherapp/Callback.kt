package com.example.kehtolaulu.weatherapp

import retrofit2.Response

interface Callback {
    fun onSuccess(response: Response<CitiesForecast>?)
}
