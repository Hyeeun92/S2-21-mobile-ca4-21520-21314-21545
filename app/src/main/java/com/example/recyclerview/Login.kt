package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Login() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val getEmail = findViewById<EditText>(R.id.id_email)
        val getPswd = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)

        var sharedPreferences = getSharedPreferences("USER_INFO", MODE_PRIVATE)
        val email = sharedPreferences.getString("EMAIL", "")
        val pswd = sharedPreferences.getString("PASSWORD", "")

        loginButton.setOnClickListener {
            val inputEmail = getEmail.text.toString()
            val inputPswd = getPswd.text.toString()

            if (inputEmail == email){
                if (pswd == inputPswd) {

                    val intent = Intent(this, StationList::class.java)
                    startActivity(intent)
                }
            }
        }




    }
}