package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

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

        //set button click listener to change activity to Dublin weather
        btnDublin.setOnClickListener{
            //use Intent to change
            val intent = Intent(this, DublinWeather::class.java)
            startActivity(intent)
        }

        //set button click listener to change activity to Paris weather
        btnParis.setOnClickListener{
            //use Intent to change
            val intent = Intent(this, ParisWeather::class.java)
            startActivity(intent)
        }

        //Initialize bottom navigation
        bottom_navigation.selectedItemId = R.id.page_4
        //set selected item in navigation
        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
}