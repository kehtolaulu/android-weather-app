package com.example.kehtolaulu.weatherapp

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ForecastAdapter(diffCallback: DiffUtil.ItemCallback<CitiesForecast.City>, private val callback: Callback) : ListAdapter<CitiesForecast.City, ForecastAdapter.ForecastHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast, parent, false)
        return ForecastHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ForecastHolder, position: Int) {
        viewHolder.id = position
        viewHolder.tvCity.text = getItem(position).name
        viewHolder.tvCountry.text = getItem(position).sys!!.country
        viewHolder.tvTemp.text = getItem(position).main!!.temp.toString()
        viewHolder.itemView.setOnClickListener {
            callback.callback(viewHolder.id)
        }
    }

    inner class ForecastHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var id: Int = 0
        var tvCity: TextView = view.findViewById(R.id.tv_city)
        var tvCountry: TextView = view.findViewById(R.id.tv_country)
        var tvTemp: TextView = view.findViewById(R.id.tv_temperature)
    }
}