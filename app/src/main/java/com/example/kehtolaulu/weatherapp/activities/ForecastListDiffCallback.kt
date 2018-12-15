package com.example.kehtolaulu.weatherapp.activities

import android.support.v7.util.DiffUtil
import com.example.kehtolaulu.weatherapp.entities.CitiesForecast

class ForecastListDiffCallback : DiffUtil.ItemCallback<CitiesForecast.City>() {

    override fun areItemsTheSame(oldItem: CitiesForecast.City, newItem: CitiesForecast.City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CitiesForecast.City, newItem: CitiesForecast.City): Boolean {
        return oldItem == newItem
    }
}