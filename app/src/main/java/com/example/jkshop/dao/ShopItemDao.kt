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

    /**
     *  初始化商品資料
     */
    @Insert
    fun insertShopItemList(shopList: List<ShopItemEntity>)

    /**
     *  取得商品列表(採分頁功能)
     *  @param pageCount 每頁筆數
     *  @param offset 開始查詢的 position(e.g 如果想從第 11 個開始查，就是 OFFSET 11)
     */
    @Query("SELECT * FROM ShopItemEntity LIMIT :pageCount OFFSET :offset")
    fun getShopItemList(pageCount: Int, offset: Int): List<ShopItemEntity>

    /**
     *  取得商品詳細資訊
     */
    @Query("SELECT * FROM ShopItemEntity WHERE shop_id = (:id)")
    fun getShopItemDetail(id: String): ShopItemEntity

    /**
     *  商品加入購物車
     */
    @Insert
    fun insertItemToShopCart(shopCartItem: ShoppingCartEntity)

    /**
     *  取得購物車內商品的列表
     */
    @Query("SELECT * FROM ShoppingCartEntity INNER JOIN ShopItemEntity ON buyerShopItemId = shop_id" +
            " WHERE buyerUserName = (:userName)")
    fun getShoppingCartItemList(userName: String): List<ShopItemEntity>

    /**
     *  清空購物車
     */
    @Query("DELETE FROM ShoppingCartEntity WHERE buyerUserName = (:userName)")
    fun clearShoppingCartByUserName(userName: String)
}