package com.example.kehtolaulu.weatherapp.interfaces

import com.example.kehtolaulu.weatherapp.entities.CitiesForecast
import retrofit2.Response

interface Callback {
    fun onSuccess(response: Response<CitiesForecast>?)
}
