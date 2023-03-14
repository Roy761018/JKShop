package com.example.jkshop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "user_name") val userName: String
)