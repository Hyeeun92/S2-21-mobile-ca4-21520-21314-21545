@file:Suppress("DEPRECATION")
package com.example.recyclerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_recycler.*
import okhttp3.*
import java.io.IOException

class StationListDublin : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)

        Json()

        bottom_navigation.selectedItemId = R.id.page_2

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        nav.setOnNavigationItemReselectedListener(this)
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId) {
            R.id.page_1 -> {
                val intent = Intent(this, MapsActivityDublin::class.java)
                startActivity(intent)
            }
            R.id.page_4 -> {
                val intent = Intent(this, ChooseCity::class.java)
                startActivity(intent)
            }


        }
    }


    fun Json() {

        val url = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=7283e9b8e9caa0f68b1afa90e6472e58c599ea00"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().create()

                val stations = gson.fromJson(body, Array<bikeStation>::class.java)

                runOnUiThread{
                   recyclerView.adapter = RecyclerAdapter(stations)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }


        })
    }



}

