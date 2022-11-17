package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: MyDatabase? = null
        fun getDb(context: Context): MyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MyDatabase::class.java, "database-name")
                    .build()
            }
            return instance as MyDatabase
        }
    }
}