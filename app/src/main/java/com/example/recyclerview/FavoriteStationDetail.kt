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
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteStationDetail : AppCompatActivity(), OnMapReadyCallback, BottomNavigationView.OnNavigationItemReselectedListener{

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_detail)

        //Initialize for data from previous class - using intent
        val stationCity = intent.getStringExtra(FavoriteAdapter.CITY_KEY)
        val stationAddress = intent.getStringExtra(FavoriteAdapter.ADDRESS_KEY)
        val aBikeStation = intent.getStringExtra(FavoriteAdapter.ABIKESTAND)
        val aBike = intent.getStringExtra(FavoriteAdapter.ABIKE)

        //Initialize item to print the data
        val city = findViewById<TextView>(R.id.stationContry)
        val address = findViewById<TextView>(R.id.station_address)
        val availableBikestand = findViewById<TextView>(R.id.station_aBikeStand)
        val availableBike = findViewById<TextView>(R.id.station_aBike)

        city.text = stationCity
        address.text = stationAddress
        availableBikestand.text = aBikeStation
        availableBike.text = aBike

        //Initialize for map fragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        //Initialize bottom navigation
        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //set selected item in navigation
        nav.selectedItemId = R.id.page_3
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

        //get address data from previous class - using intent
        val stationAddress = intent.getStringExtra(RecyclerAdapter.ADDRESS_KEY)

        //Initialize lat and lng data for map  - using intent
        val lat = intent.getDoubleExtra(FavoriteAdapter.POSITION_LAT_KEY, 0.00000)
        val lng = intent.getDoubleExtra(FavoriteAdapter.POSITION_LNG_KEY, 0.00000)
        val station = LatLng(lat, lng)
        val zoomLevel = 15f

        //make marker and move camera with lat and lng
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(station, zoomLevel))
        map.addMarker(MarkerOptions().position(station).title(stationAddress))

    }
}