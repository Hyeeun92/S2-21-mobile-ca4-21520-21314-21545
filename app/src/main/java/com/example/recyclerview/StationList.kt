@file:Suppress("DEPRECATION")
package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_recycler.*
import okhttp3.*
import java.io.IOException

class StationList : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener {

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
            R.id.page_5 -> {
                val intent = Intent(this, ManageAccount::class.java)
                startActivity(intent)
            }
        }
    }


    fun Json() {

        val url = "https://api.jcdecaux.com/vls/v1/stations?contract?&apiKey=7283e9b8e9caa0f68b1afa90e6472e58c599ea00"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().create()

                val stations = gson.fromJson(body, Array<bikeStation>::class.java)

                runOnUiThread{
                    val filtered: List<bikeStation> = stations.filter {it.contract_name == "dublin" || it.contract_name == "marseille"}
                    recyclerView.adapter = RecyclerAdapter(filtered)

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

        })
    }




}



