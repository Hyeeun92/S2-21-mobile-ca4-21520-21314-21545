package com.example.recyclerview

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_station_detail.*

class StationDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)
/*
        val stationCity = intent.getStringExtra(RecyclerAdapter.CITY_KEY)
        val stationAddress = intent.getStringExtra(RecyclerAdapter.ADDRESS_KEY)
        val aBikeStation = intent.getStringExtra(RecyclerAdapter.ABIKESTAND)
        val aBike = intent.getStringExtra(RecyclerAdapter.ABIKE)*/

        val lat = intent.getStringExtra(RecyclerAdapter.POSITION_LAT_KEY)?.toDouble()
        val lng = intent.getStringExtra(RecyclerAdapter.POSITION_LNG_KEY)?.toDouble()

        val city = findViewById<TextView>(R.id.stationContry)
        val address = findViewById<TextView>(R.id.station_address)
        val availableBikestand = findViewById<TextView>(R.id.station_aBikeStand)
        val availableBike = findViewById<TextView>(R.id.station_aBike)

        city.text = lat?.toString()
        address.text = lng?.toString()


        /*city.text = stationCity
        address.text = stationAddress
        availableBikestand.text = aBikeStation
        availableBike.text = aBike*/

    }

}