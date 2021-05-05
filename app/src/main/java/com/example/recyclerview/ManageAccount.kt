package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ManageAccount : AppCompatActivity(), BottomNavigationView.OnNavigationItemReselectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_account)

        //Initialize buttons and item
        val id_btnchange = findViewById<Button>(R.id.id_btnchange)
        val id_email = findViewById<EditText>(R.id.id_email)
        val id_password = findViewById<EditText>(R.id.id_password)
        val id_newpassword = findViewById<EditText>(R.id.id_newpassword)
        val id_confirmpassword = findViewById<EditText>(R.id.id_confirm)

        //initialize share preferences to save and get data
        var sharedPreferences = getSharedPreferences("USER_INFO", MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()

        //initialize to get data from shared preferences
        val savedEmail = sharedPreferences.getString("EMAIL", "")
        val savedPswd = sharedPreferences.getString("PASSWORD", "")
        val firstName = sharedPreferences.getString("FIRSTNAME", "")
        val lastName = sharedPreferences.getString("LASTNAME", "")

        id_btnchange.setOnClickListener {
            val email = id_email.text.toString()
            val password = id_password.text.toString()
            val newpassword = id_newpassword.text.toString()
            val confirm = id_confirmpassword.text.toString()
            //check if input new password and confirm password are matched
            if (newpassword == confirm) {
                //check if input email and saved email are match and input password and saved password are match
                if (email == savedEmail && savedPswd == password) {
                    //save new password and other information to shared preferences
                    editor.apply {
                        putString("EMAIL", email)
                        putString("PASSWORD", newpassword)
                        putString("FIRSTNAME", firstName)
                        putString("LASTNAME", lastName)
                    }.apply()
                    //change screen to log in after save new information
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                }
            }
        }
        //Initialize bottom navigation
        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //set selected item in navigation
        nav.selectedItemId = R.id.page_5
        //navigation item listener
        nav.setOnNavigationItemReselectedListener(this)
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        when(item.itemId) {
            //If user select menu - map : change screen to choose city
            R.id.page_1 -> {
                //use intent to change screen
                val intent = Intent(this, ChooseCity::class.java)
                startActivity(intent)
            }
            //If user select menu - list : change screen to station list
            R.id.page_2 -> {
                //use intent to change screen
                val intent = Intent(this, StationList::class.java)
                startActivity(intent)
            }
            //If user select menu - favorite list : change screen to favorite list
            R.id.page_3 -> {
                //use intent to change screen
                val intent = Intent(this, FavoriteStation::class.java)
                startActivity(intent)
            }
            //If user select menu - weather : change screen to choose city for weather
            R.id.page_4 -> {
                //use intent to change screen
                val intent = Intent(this, ChooseCityWeather::class.java)
                startActivity(intent)
            }
            //If user select menu - setting : change screen to manage account
            R.id.page_5 -> {
                //use intent to change screen
                val intent = Intent(this, ManageAccount::class.java)
                startActivity(intent)
            }
        }
    }
}
