package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userName = findViewById<EditText>(R.id.userName)
        val button = findViewById<Button>(R.id.submitUserName)
        button.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("userName", userName.text.toString())
            startActivity(intent)
        }
    }
}