package com.example.recyclerview

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_recycler.*

class ManageAccount : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_account)

        bottom_navigation.selectedItemId = R.id.page_5

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        nav.setOnNavigationItemReselectedListener(this)

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
                } else {
                    val toast = Toast.makeText(applicationContext, "Incorrect email or password", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
                    toast.show()
                }
            }
        }
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when (item.itemId) {
            R.id.page_1 -> {
                val intent = Intent(this, ChooseCity::class.java)
                startActivity(intent)
            }
            R.id.page_2 -> {
                val intent = Intent(this, StationList::class.java)
                startActivity(intent)
            }
            R.id.page_3 -> {
                val intent = Intent(this, FavoriteStation::class.java)
                startActivity(intent)
            }

            R.id.page_4 -> {
                val intent = Intent(this, ChooseCityWeather::class.java)
                startActivity(intent)
            }
            R.id.page_5 -> {
                val intent = Intent(this, ManageAccount::class.java)
                startActivity(intent)
            }
        }
    }
}
