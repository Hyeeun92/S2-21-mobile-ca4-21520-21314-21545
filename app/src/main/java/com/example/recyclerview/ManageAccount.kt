package com.example.recyclerview

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ManageAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_account)

        val id_btnchange = findViewById<Button>(R.id.id_btnchange)
        val id_email = findViewById<EditText>(R.id.id_email)
        val id_password = findViewById<EditText>(R.id.id_password)
        val id_newpassword = findViewById<EditText>(R.id.id_newpassword)
        val id_confirmpassword = findViewById<EditText>(R.id.id_confirm)

        var sharedPreferences = getSharedPreferences("USER_INFO", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()

        val savedEmail = sharedPreferences.getString("EMAIL", "")
        val savedPswd = sharedPreferences.getString("PASSWORD", "")
        val firstName = sharedPreferences.getString("FIRSTNAME", "")
        val lastName = sharedPreferences.getString("LASTNAME", "")

        id_btnchange.setOnClickListener {
            val email = id_email.text.toString()
            val password = id_password.text.toString()
            val newpassword = id_newpassword.text.toString()
            val confirm = id_confirmpassword.text.toString()
            if (newpassword == confirm) {
                if (email == savedEmail && savedPswd == password) {
                    editor.apply {
                        putString("EMAIL", email)
                        putString("PASSWORD", newpassword)
                        putString("FIRSTNAME", firstName)
                        putString("LASTNAME", lastName)
                    }.apply()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
