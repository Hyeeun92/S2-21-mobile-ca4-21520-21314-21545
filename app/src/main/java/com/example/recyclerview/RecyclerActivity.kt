@file:Suppress("DEPRECATION")
package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_recycler.*
import okhttp3.*
import java.io.IOException

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        recyclerView.layoutManager = LinearLayoutManager(this)

        Json()

    }

    fun Json() {
        //val url = "https://api.jcdecaux.com/vls/v1/stations?apiKey=7283e9b8e9caa0f68b1afa90e6472e58c599ea00"

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
