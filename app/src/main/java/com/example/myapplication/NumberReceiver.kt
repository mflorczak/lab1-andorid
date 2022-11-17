package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NumberReceiver: BroadcastReceiver() {




    override fun onReceive(context: Context?, intent: Intent?) {
        CoroutineScope(IO).launch {
            val userName = intent?.getStringExtra("name")!!
            val count = intent.getIntExtra("count", 0)
            Log.i("NumberReceiver", "User name is: $userName and counter stopped at: $count")

            MyDatabase
                .getDb(context!!)
                .userDao()
                .insert(User(userName, count))
        }
    }
}