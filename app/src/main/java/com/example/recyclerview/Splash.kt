@file:Suppress("DEPRECATION")
package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)
        //hide toolbar
        this.supportActionBar?.hide()

        Handler().postDelayed({
            //open new activity after DURATION
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },DURATION)
    }
    //set duration for splash
    companion object {
        private const val DURATION : Long = 3000
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
}