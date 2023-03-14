package com.example.jkshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 *  用戶相關功能
 */

@Dao
interface UserDao {

    @Query("SELECT user_name FROM UserEntity WHERE uid IN (:userName)")
    fun getUser(userName: String) : String?

    @Insert
    fun userRegister(userName: String)
}