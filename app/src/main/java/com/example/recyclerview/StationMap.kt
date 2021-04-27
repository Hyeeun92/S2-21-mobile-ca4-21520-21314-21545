package com.example.recyclerview

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class StationMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_station_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


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

