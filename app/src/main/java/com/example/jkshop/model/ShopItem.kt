package com.example.jkshop.model

data class ShopItem(
    val name: String,
    val description: String,
    val price: Int = 0,
    val createTime: String,
    val img: String
)
