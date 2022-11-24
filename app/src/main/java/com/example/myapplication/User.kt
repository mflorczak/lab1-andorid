package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = User.TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val count: Int
) {
    companion object {
        const val TABLE_NAME = "user"
    }

    constructor(name: String, count: Int) : this(0, name, count)
}
