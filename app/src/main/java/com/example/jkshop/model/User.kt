package com.example.jkshop.model

data class User (
    val userName: String?,
    val orderList: ArrayList<ShopOrder>? = null
)