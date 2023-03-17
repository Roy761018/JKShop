package com.example.jkshop.model

data class ShopOrder(
    val shopItemEntityList: ArrayList<ShopItemEntity>,
    val orderCreateTime: String,
    val orderPrice: Int
)
