package com.example.kehtolaulu.weatherapp

import android.support.v7.util.DiffUtil

class ForecastListDiffCallback : DiffUtil.ItemCallback<CitiesForecast.City>() {

    override fun areItemsTheSame(oldItem: CitiesForecast.City, newItem: CitiesForecast.City): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CitiesForecast.City, newItem: CitiesForecast.City): Boolean {
        return oldItem == newItem
    }
}