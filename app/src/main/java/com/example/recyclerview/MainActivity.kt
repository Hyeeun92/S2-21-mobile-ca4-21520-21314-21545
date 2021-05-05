@file:Suppress("DEPRECATION")
package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar?.hide()

        //Initialize button for create account or log in
        val id_btnLogIn = findViewById<Button>(R.id.id_btnLogIn)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccaunt)

        id_btnLogIn.setOnClickListener {
            // launch the login activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        btnCreateAccount.setOnClickListener {
            // launch the create account activity
            val intent = Intent(this, CreateNewAccount::class.java)
            startActivity(intent)
        }
    }
}