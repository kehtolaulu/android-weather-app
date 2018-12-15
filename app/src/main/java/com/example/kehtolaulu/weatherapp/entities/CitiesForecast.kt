package com.example.kehtolaulu.weatherapp.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "forecast")
class CitiesForecast {
    @PrimaryKey(autoGenerate = true)
    private val id: Int? = null
    @SerializedName("list")
    internal var list: List<City>? = null

    @Entity(tableName = "city")
    inner class City {
        @PrimaryKey(autoGenerate = true)
        private val id: Int? = null
        @ColumnInfo(name = "name")
        @SerializedName("name")
        internal var name: String? = null
        @SerializedName("sys")
        internal var sys: Sys? = null
        @SerializedName("main")
        internal var main: WeatherTemp? = null
        @SerializedName("wind")
        internal var wind: Wind? = null
        @Entity(tableName = "sys")
        inner class Sys {
            @ColumnInfo(name = "country")
            internal var country: String? = null
        }
        @Entity(tableName = "weathertemp")
        inner class WeatherTemp {
            @PrimaryKey(autoGenerate = true)
            private val id: Int? = null
            @ColumnInfo(name = "temp")
            internal var temp: Double? = null
            @ColumnInfo(name = "pressure")
            internal var pressure: Double? = null
            @ColumnInfo(name = "humidity")
            internal var humidity: Double? = null
        }
        @Entity(tableName = "wind")
        inner class Wind {
            @PrimaryKey(autoGenerate = true)
            private val id: Int? = null
            @ColumnInfo(name = "deg")
            internal var deg: Double? = null
        }
    }
}
