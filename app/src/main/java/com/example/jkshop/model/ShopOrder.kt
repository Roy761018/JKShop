package com.example.jkshop.model

data class ShopOrder(
    val shopItemList: ArrayList<ShopItem>,
    val orderCreateTime: String,
    val orderPrice: Int
)
