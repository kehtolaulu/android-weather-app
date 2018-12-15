package com.example.kehtolaulu.weatherapp.network

import com.example.kehtolaulu.weatherapp.entities.CitiesForecast
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.facebook.stetho.okhttp3.StethoInterceptor


object WeatherApi {
    const val KEY = "07e3f60ab7ce0ff2bf14da7b71011858"
    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    private var retrofit: Retrofit? = null

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

    private var sClient: OkHttpClient? = null

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun getClient(): OkHttpClient? {
        var client = sClient
        if (client == null) {
            synchronized(WeatherApi::class.java) {
                client = sClient
                if (client == null) {
                    sClient = buildClient()
                    client = sClient
                }
            }
        }
        return client
    }

    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(StethoInterceptor())
                .build()
    }
}