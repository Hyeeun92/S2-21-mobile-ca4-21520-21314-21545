package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCityWeather : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        btnDublin.setOnClickListener{
            val intent = Intent(this, DublinWeather::class.java)
            startActivity(intent)
        }

        btnParis.setOnClickListener{
            val intent = Intent(this, ParisWeather::class.java)
            startActivity(intent)
        }

        bottom_navigation.selectedItemId = R.id.page_4
        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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