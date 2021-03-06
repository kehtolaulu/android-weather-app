package com.example.kehtolaulu.weatherapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import retrofit2.Response
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task


class ListActivity : AppCompatActivity(), Callback, PositionCallback {
    private var forecastAdapter: ForecastAdapter? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        forecastAdapter = ForecastAdapter(ForecastListDiffCallback(), this)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_forecast)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = forecastAdapter
        checkPerm()?.addOnSuccessListener { location: Location? ->
            // Got last known location. In some rare situations this can be null.
            var lat: Double
            var lng: Double
            if (location == null) {
                lat = 55.78874
                lng = 49.12214
            } else {
                lat = location.latitude
                lng = location.longitude
            }
            Weather.getWeather(lat, lng, this, this)
        }
    }

    override fun onSuccess(response: Response<CitiesForecast>?) {
        forecastAdapter?.submitList(response?.body()?.list)
    }

    override fun callback(position: Int) {
        val intent = Intent(this, ForecastActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    private fun checkPerm(): Task<Location>? {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    0)
        } else {
            return fusedLocationClient.lastLocation
        }
        return null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            0 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkPerm()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        0)
            }
        }
    }


}

