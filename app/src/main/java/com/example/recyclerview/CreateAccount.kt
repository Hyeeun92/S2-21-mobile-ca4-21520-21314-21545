package com.example.recyclerview

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateAccaunt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create__accaunt)

        val id_btnCreateNewAccount = findViewById<Button>(R.id.id_btnCreateNewAccount)
        val id_email = findViewById<EditText>(R.id.id_email)
        val id_password = findViewById<EditText>(R.id.id_password)
        val id_firstname = findViewById<EditText>(R.id.id_firstname)
        val id_lastname = findViewById<EditText>(R.id.id_lastname)

         id_btnCreateNewAccount.setOnClickListener{
            val email = id_email.text.toString()
            val pswd = id_password.text.toString()
            val firstName = id_firstname.text.toString()
            val lastName = id_lastname.text.toString()

            print("Email is:  + email")

        }

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Create Account"

            //back button
            actionBar.setDisplayHomeAsUpEnabled(true)

        }


    }
}