package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    private lateinit var receiver: NumberReceiver

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

        val db = MyDatabase.getDb(this)

        findViewById<Button>(R.id.userDetailsButton).setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().getAll().forEach {
                    Log.i("UserActivity", "User: ${it.name} count: ${it.count}")
                }
            }
        }

        receiver = NumberReceiver()
        IntentFilter("com.example.broadcast.SERVICE_STOPPED_NOTIFICATION").also {
            registerReceiver(receiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}