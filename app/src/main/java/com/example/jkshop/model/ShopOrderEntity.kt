package com.example.jkshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopOrderEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val orderID: String,
    val buyerUsername: String,
    val shopItemEntityList: ArrayList<ShopItemEntity>,
    val orderCreateTime: String,
    val orderPrice: Int
)