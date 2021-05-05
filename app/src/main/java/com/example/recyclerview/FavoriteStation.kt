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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_recycler.*
import okhttp3.*
import java.io.IOException

class FavoriteStation: AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener {

    var listType : TypeToken<MutableList<favList>> = object : TypeToken<MutableList<favList>>() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)

        Json()


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

    fun Json() {
        //Initialize api key and json url
        val api_key = "7283e9b8e9caa0f68b1afa90e6472e58c599ea00"
        val url = "https://api.jcdecaux.com/vls/v1/stations?contract?&apiKey=$api_key"

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        //call json data
        client.newCall(request).enqueue(object: Callback {

            override fun onResponse(call: Call, response: Response) {

                val body = response?.body?.string()
                val gson = GsonBuilder().create()
                val stations = gson.fromJson(body, Array<bikeStation>::class.java)

                //get from share preferences that saved address
                val getPref = App.prefs.getS("1")
                //make list with shared preferences data
                val data1: MutableList<favList> = gson.fromJson(getPref, listType.type)
                val itemList : MutableList<String> = mutableListOf()

                runOnUiThread{
                    val checkList: MutableList<bikeStation> = mutableListOf()
                    //get each address in arraylist
                    data1.forEach {
                        val item = it.address
                        itemList.add(item)
                    }
                    //check if the address from shared preferences is same in open api
                    for(index in 0 until itemList.size) {
                        //if it is exist make new list for this
                        val List: List<bikeStation> = stations.filter { it.address == itemList.get(index)}
                        checkList += List
                    }
                    //sending to recycler view
                    recyclerView.adapter = FavoriteAdapter(checkList)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

}



