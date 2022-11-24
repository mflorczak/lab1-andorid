package com.example.myapplication

import android.database.Cursor;
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomMasterTable.TABLE_NAME

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM " + User.TABLE_NAME)
    fun selectAll(): Cursor
}