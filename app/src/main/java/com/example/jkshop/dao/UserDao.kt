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

    /**
     *  取得用戶資料
     */
    @Query("SELECT user_name FROM UserEntity WHERE user_name IN (:userName)")
    fun getUser(userName: String) : String?

    /**
     *  用戶註冊
     */
    @Insert
    fun userRegister(user: UserEntity)
}