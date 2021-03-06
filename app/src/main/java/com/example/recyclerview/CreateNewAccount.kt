@file:Suppress("DEPRECATION")
package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateNewAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        //Initialize variables - view(activity_create_account) by id
        val id_btnCreateNewAccount = findViewById<Button>(R.id.id_btnchange)
        val id_email = findViewById<EditText>(R.id.id_email)
        val id_password = findViewById<EditText>(R.id.id_password)
        val id_firstname = findViewById<EditText>(R.id.id_newpassword)
        val id_lastname = findViewById<EditText>(R.id.id_confirm)

        //Initialize shared preferences to save account information
        var sharedPreferences = getSharedPreferences("USER_INFO", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()

        //add button click listener to save information
        id_btnCreateNewAccount.setOnClickListener{
            //Initialize for user input
            val email = id_email.text.toString()
            val pswd = id_password.text.toString()
            val firstName = id_firstname.text.toString()
            val lastName = id_lastname.text.toString()

            //add in shared preferences
            editor.apply {
                putString("EMAIL", email)
                putString("PASSWORD", pswd)
                putString("FIRSTNAME", firstName)
                putString("LASTNAME", lastName)
            }.apply()

            //use intent to change screen - log in screen
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }


    }
}