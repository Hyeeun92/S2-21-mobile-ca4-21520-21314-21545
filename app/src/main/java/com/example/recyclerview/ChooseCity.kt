@file:Suppress("DEPRECATION")
package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCity() : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener {

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        val actionBar = supportActionBar
        actionBar!!.title = "Choose city"



        btnParis.setOnClickListener {
            val intent = Intent(this, MapsActivityParis::class.java)

                startActivity(intent)

        }

        btnDublin.setOnClickListener {
            val intent = Intent(this, MapsActivityDublin::class.java)
                startActivity(intent)

        }

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        nav.selectedItemId = R.id.page_1
        nav.setOnNavigationItemReselectedListener(this)



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
            R.id.page_3 -> {
                val intent = Intent(this, FavoriteStation::class.java)
                startActivity(intent)
            }

            R.id.page_4 -> {
                val intent = Intent(this, ChooseCityWeather::class.java)
                startActivity(intent)
            }
        }
    }

}

