package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash_Screen : AppCompatActivity() {
    private  val SPLASH_TIME: Long = 3000

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)
        handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java ))
            finish()
        } , SPLASH_TIME)


    }
}