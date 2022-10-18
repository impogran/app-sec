package com.example.projektam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FirstActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_first)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val thread = Thread{
            run{
                Thread.sleep(4000)
            }
            runOnUiThread{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.onStop()
            }
        }
        thread.start()
    }
}