package com.example.kehtolaulu.weatherapp.database

import android.arch.persistence.room.*
import com.example.kehtolaulu.weatherapp.entities.CitiesForecast
import com.example.kehtolaulu.weatherapp.entities.CitiesForecast.City


@Dao
interface MyDao {
    @Insert
    fun insertAll(cities: CitiesForecast)

    @Delete
    fun delete(city: City)

    @Query("select * from forecast")
    fun getAll() : List<CitiesForecast.City>
}