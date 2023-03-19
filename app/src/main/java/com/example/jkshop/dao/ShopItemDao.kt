package com.example.jkshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShoppingCartEntity

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

    @Insert
    fun insertItemToShopCart(shopCartItem: ShoppingCartEntity)

    @Query("SELECT * FROM ShoppingCartEntity INNER JOIN ShopItemEntity ON buyerShopItemId = shop_id" +
            " WHERE buyerUserName in (:userName)")
    fun getShoppingCartItemList(userName: String): List<ShopItemEntity>?

    @Query("DELETE FROM ShoppingCartEntity WHERE buyerUserName = (:userName)")
    fun clearShoppingCartByUserName(userName: String)
}