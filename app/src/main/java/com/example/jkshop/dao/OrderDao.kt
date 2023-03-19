package com.example.jkshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.model.UserEntity

/**
 *  訂單相關功能
 */

@Dao
interface OrderDao {

    @Insert
    fun generateOrder(orderEntity: ShopOrderEntity)

    @Query("SELECT * FROM ShopOrderEntity WHERE buyerUsername = (:userName)")
    fun getUserOrder(userName: String): ShopOrderEntity?
}