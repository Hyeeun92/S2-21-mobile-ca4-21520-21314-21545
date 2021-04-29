@file:Suppress("DEPRECATION")
package com.example.recyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_choose_city.*
import kotlinx.android.synthetic.main.activity_recycler.*

class ChooseCity : AppCompatActivity() {


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


    }
}