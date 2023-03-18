package com.example.jkshop.model

import androidx.room.Entity

@Entity
data class ShopOrder(
    val shopItemEntityList: ArrayList<ShopItemEntity>,
    val orderCreateTime: String,
    val orderPrice: Int
)
