package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_recycler.*

class FavoriteStation: AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)

        bottom_navigation.selectedItemId = R.id.page_3

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        nav.setOnNavigationItemReselectedListener(this)



       /* val favList = stations.get()

        recyclerView.adapter = FavoriteAdapter(favList)*/

        /*   val stationCity = intent.getStringExtra(StationDetail.CITY_KEY)
           val stationAddress = intent.getStringExtra(StationDetail.ADDRESS_KEY)
           val aBikeStation = intent.getStringExtra(StationDetail.ABIKESTAND)
           val aBike = intent.getStringExtra(StationDetail.ABIKE)
           val lat = intent.getDoubleExtra(StationDetail.POSITION_LAT_KEY, 0.00000)
           val lng = intent.getDoubleExtra(StationDetail.POSITION_LNG_KEY, 0.00000)

           val favLists = ArrayList<favstation>()
           favLists.addAll(listOf(favstation(stationCity, stationAddress, aBikeStation, aBike, lat, lng)))

           runOnUiThread {

               recyclerView.adapter = FavoriteAdapter(favLists)
           }*/

    }


    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId) {
            R.id.page_1 -> {
                val intent = Intent(this, ChooseCity::class.java)
                startActivity(intent)
            }
            R.id.page_2 -> {
                val intent = Intent(this, StationList::class.java)
                startActivity(intent)
            }
            R.id.page_4 -> {
                val intent = Intent(this, ChooseCity::class.java)
                startActivity(intent)
            }


        }
    }
}



