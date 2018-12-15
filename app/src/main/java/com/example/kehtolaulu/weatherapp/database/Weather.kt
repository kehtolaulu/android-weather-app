package com.example.kehtolaulu.weatherapp.database

import android.arch.persistence.room.*
import com.example.kehtolaulu.weatherapp.entities.CitiesForecast

@Entity(tableName = "weather")
data class Weather(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var weather: CitiesForecast?
)
