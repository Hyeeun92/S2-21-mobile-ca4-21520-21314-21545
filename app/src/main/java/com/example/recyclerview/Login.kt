package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Login() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Initialize items by id
        val getEmail = findViewById<EditText>(R.id.id_email)
        val getPswd = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)

        //Initialize shared preferences for get data
        var sharedPreferences = getSharedPreferences("USER_INFO", MODE_PRIVATE)
        //get data from shared preferences file
        val email = sharedPreferences.getString("EMAIL", "")
        val pswd = sharedPreferences.getString("PASSWORD", "")

        //set click listener for compare input id, password and saved id,  password
        loginButton.setOnClickListener {
            val inputEmail = getEmail.text.toString()
            val inputPswd = getPswd.text.toString()

            if (inputEmail == email) {
                if (pswd == inputPswd) {
                    //If id and password is matched with shared preferences and user input - open the new screen
                    val intent = Intent(this, ChooseCity::class.java)
                    startActivity(intent)
                }

            }

        }
    }
}