@file:Suppress("DEPRECATION")

package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Splash_Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen2)

        val id_btnLogIn = findViewById<Button>(R.id.id_btnLogIn)
        val btnCreateAccaunt = findViewById<Button>(R.id.btnCreateAccaunt)

        id_btnLogIn.setOnClickListener {
          Log.d("Splash_Screen2", "Try login")
            // launch the login activity
            val intent = Intent(this, Login::class.java)
                startActivity(intent)

        }

        btnCreateAccaunt.setOnClickListener {
            Log.d("Splash_Screen2", "Try create")
            // launch the create account activity
            val intent = Intent(this, CreateNewAccount::class.java)
            startActivity(intent)

        }

        }







    }





