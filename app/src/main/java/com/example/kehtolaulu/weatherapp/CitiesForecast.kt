package com.example.kehtolaulu.weatherapp

import com.google.gson.annotations.SerializedName

class CitiesForecast {
    @SerializedName("list")
    internal var list: List<City>? = null

    inner class City {
        @SerializedName("name")
        internal var name: String? = null
        @SerializedName("sys")
        internal var sys: Sys? = null
        @SerializedName("main")
        internal var main: WeatherTemp? = null
        @SerializedName("wind")
        internal var wind: Wind? = null

        inner class Sys {
            internal var country: String? = null
        }

        inner class WeatherTemp {
            internal var temp: Double? = null
            internal var pressure: Double? = null
            internal var humidity: Double? = null
        }

        inner class Wind {
            internal var speed: Double? = null
            internal var deg: Double? = null
            internal var gust: Double? = null
        }
    }
}
