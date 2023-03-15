package com.example.jkshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jkshop.model.UserEntity

/**
 *  用戶相關功能
 */

@Dao
interface UserDao {

    @Query("SELECT user_name FROM UserEntity WHERE user_name IN (:userName)")
    fun getUser(userName: String) : String?

    @Insert
    fun userRegister(user: UserEntity)
}