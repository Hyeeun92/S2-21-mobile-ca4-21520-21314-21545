package com.example.recyclerview

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.activity_station_detail.*
import kotlinx.android.synthetic.main.activity_station_detail.bottom_navigation

class StationDetailParis : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1


    companion object {
        val CITY_KEY = "CITY"
        val ADDRESS_KEY = "ADDRESS"
        val POSITION_LAT_KEY = "POSITION_LAT"
        val POSITION_LNG_KEY = "POSITION_LNG"
        val ABIKESTAND = "AVAILABLE_BIKE_STNADS"
        val ABIKE = "AVAILABLE_BIKES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)

        val stationCity = intent.getStringExtra(RecyclerAdapter.CITY_KEY)
        val stationAddress = intent.getStringExtra(RecyclerAdapter.ADDRESS_KEY)
        val aBikeStation = intent.getStringExtra(RecyclerAdapter.ABIKESTAND)
        val aBike = intent.getStringExtra(RecyclerAdapter.ABIKE)
        val lat = intent.getDoubleExtra(RecyclerAdapter.POSITION_LAT_KEY, 0.00000)
        val lng = intent.getDoubleExtra(RecyclerAdapter.POSITION_LNG_KEY, 0.00000)

        val city = findViewById<TextView>(R.id.stationContry)
        val address = findViewById<TextView>(R.id.station_address)
        val availableBikestand = findViewById<TextView>(R.id.station_aBikeStand)
        val availableBike = findViewById<TextView>(R.id.station_aBike)

        city.text = stationCity
        address.text = stationAddress
        availableBikestand.text = aBikeStation
        availableBike.text = aBike

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottom_navigation.selectedItemId = R.id.page_2

        val checkbox = findViewById<Button>(R.id.checkBox)

        checkbox.setOnClickListener {

            val intent = Intent(this, FavoriteStation::class.java)
            intent.putExtra(CITY_KEY, stationCity)
            intent.putExtra(ADDRESS_KEY, stationAddress)
            intent.putExtra(ABIKESTAND, aBikeStation)
            intent.putExtra(ABIKE, aBike)

            intent.putExtra(POSITION_LAT_KEY, lat)
            intent.putExtra(POSITION_LNG_KEY, lng)

            startActivity(intent)
        }


    }



    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap

        val stationAddress = intent.getStringExtra(RecyclerAdapter.ADDRESS_KEY)

        val lat = intent.getDoubleExtra(RecyclerAdapter.POSITION_LAT_KEY, 0.00000)
        val lng = intent.getDoubleExtra(RecyclerAdapter.POSITION_LNG_KEY, 0.00000)

        val station = LatLng(lat, lng)
        val zoomLevel = 15f

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(station, zoomLevel))
        map.addMarker(MarkerOptions().position(station).title(stationAddress))

    }

}