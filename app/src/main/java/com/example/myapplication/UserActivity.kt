package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val userName = intent.getStringExtra("userName")
        findViewById<TextView>(R.id.displayUserName).text = userName

        val startServiceButton = findViewById<Button>(R.id.startServiceButton)

        startServiceButton.setOnClickListener {
            Intent(this, CounterService::class.java).also { intent ->
                intent.putExtra("username", userName)
                startService(intent)
            }
        }

        val stopServiceButton = findViewById<Button>(R.id.stopServiceButton)
        stopServiceButton.setOnClickListener {
            Intent(this, CounterService::class.java).also { intent ->
                stopService(intent)
            }
        }
    }
}