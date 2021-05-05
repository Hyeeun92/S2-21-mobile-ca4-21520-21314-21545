package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MapsActivityParis : AppCompatActivity(), OnMapReadyCallback, BottomNavigationView.OnNavigationItemReselectedListener {

    private lateinit var mMap: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_paris)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Initialize bottom navigation
        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //set selected item in navigation
        nav.selectedItemId = R.id.page_1
        //navigation item listener
        nav.setOnNavigationItemReselectedListener(this)

    }
    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId) {
            //If user select menu - map : change screen to choose city
            R.id.page_1 -> {
                //use intent to change screen
                val intent = Intent(this, ChooseCity::class.java)
                startActivity(intent)
            }
            //If user select menu - list : change screen to station list
            R.id.page_2 -> {
                //use intent to change screen
                val intent = Intent(this, StationList::class.java)
                startActivity(intent)
            }
            //If user select menu - favorite list : change screen to favorite list
            R.id.page_3 -> {
                //use intent to change screen
                val intent = Intent(this, FavoriteStation::class.java)
                startActivity(intent)
            }
            //If user select menu - weather : change screen to choose city for weather
            R.id.page_4 -> {
                //use intent to change screen
                val intent = Intent(this, ChooseCityWeather::class.java)
                startActivity(intent)
            }
            //If user select menu - setting : change screen to manage account
            R.id.page_5 -> {
                //use intent to change screen
                val intent = Intent(this, ManageAccount::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isPermissionGranted(): Boolean {
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
            mMap.isMyLocationEnabled = true
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

    override fun onMapReady(googleMap: GoogleMap) {78797
        mMap = googleMap

        // Add a marker in Paris and move the camera
        val paris = LatLng(48.864716, 2.349014)
        mMap.addMarker(MarkerOptions().position(paris).title("Marker in Paris"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paris))

        // Add a marker in Paris and move the camera
        val paris1 = LatLng(48.870154, 2.350223)
        val paris2 = LatLng(48.867372, 2.280897)
        val paris3 = LatLng(48.872583, 2.295048)

        val zoomLevel = 15f

        mMap.addMarker(MarkerOptions().position(paris1).title("Marker in paris1"))
        mMap.addMarker(MarkerOptions().position(paris2).title("Marker in paris2"))
        mMap.addMarker(MarkerOptions().position(paris3).title("Marker in paris3"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paris3))

        //  ZOOM LEVEL
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris2, 15f))
        mMap.moveCamera((CameraUpdateFactory.newLatLngZoom(paris3, zoomLevel)))

        // ADD MARKER
        mMap.addMarker(MarkerOptions().position(paris3).title("Marker Paris3"))

        setMapLongClick(mMap)
        setPoiClick(mMap)
        enableMyLocation()
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )
            poiMarker.showInfoWindow()
        }
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapClickListener { latLng ->
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.dropped_pin))
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
            // it is around here you grab and store coordinates PREFERENCES
        }
    }
}



