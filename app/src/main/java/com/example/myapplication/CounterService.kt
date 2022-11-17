package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class CounterService : Service() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private val TAG = "CounterService"
    private var isRunning = true
    private lateinit var userName: String
    private var c by Delegates.notNull<Int>()

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scope.launch {
            var count = 1
            userName = intent?.getStringExtra("username")!!
            while (isRunning) {
                Log.i(TAG, "Counter no. $startId, v: $count")
                c = count
                count++
                Thread.sleep(1000L)
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        isRunning = false

        Intent("com.example.broadcast.SERVICE_STOPPED_NOTIFICATION").also { intent ->
            intent.putExtra("name", userName)
            intent.putExtra("count", c)
            sendBroadcast(intent)
            Log.i(TAG, "Service destroyed")
        }
    }
}