package com.example.jkshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jkshop.model.ShopOrderEntity

/**
 *  訂單相關功能
 */

@Dao
interface OrderDao {

    /**
     *  提交訂單
     */
    @Insert
    fun generateOrder(orderEntity: ShopOrderEntity)

    /**
     *  取得用戶訂單歷史紀錄
     */
    @Query("SELECT * FROM ShopOrderEntity WHERE buyerUsername = (:userName)")
    fun getUserOrderHistory(userName: String): List<ShopOrderEntity>?

    /**
     *  刪除用戶訂單歷史紀錄
     */
    @Query("DELETE FROM ShopOrderEntity WHERE order_id = (:orderID)")
    fun deleteOrderHistory(orderID: String)
}