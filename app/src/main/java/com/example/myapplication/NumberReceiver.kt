package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NumberReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val userName = intent?.getStringExtra("name");
        val count = intent?.getIntExtra("count", 0)
        Log.i("NumberReceiver", "User name is: $userName and counter stopped at: $count")
    }
}