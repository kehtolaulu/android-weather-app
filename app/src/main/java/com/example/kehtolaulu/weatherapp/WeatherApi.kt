package com.example.kehtolaulu.weatherapp

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object WeatherApi {
    const val KEY = "07e3f60ab7ce0ff2bf14da7b71011858"
    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private var retrofit: Retrofit? = null

    val client: Retrofit by lazy<Retrofit> {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    interface ApiInterface {
        @GET("find")
        fun getCitiesForecast(
                @Query("lat") lat: Double?,
                @Query("lon") lon: Double?,
                @Query("units") units: String,
                @Query("cnt") cnt: Int?,
                @Query("appid") key: String
        ): Call<CitiesForecast>
    }
}