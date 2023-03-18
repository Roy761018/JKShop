package com.example.jkshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jkshop.model.ShopItemEntity

/**
 *  商品相關功能
 */

@Dao
interface ShopItemDao {

    @Insert
    fun insertShopItemList(shopList: List<ShopItemEntity>)

    @Query("SELECT * FROM ShopItemEntity")
    fun getShopItemList(): List<ShopItemEntity>?

    @Query("SELECT * FROM ShopItemEntity WHERE shop_id in (:id)")
    fun getShopItemDetail(id: String): ShopItemEntity?
}