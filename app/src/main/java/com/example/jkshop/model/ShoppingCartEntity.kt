package com.example.jkshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingCartEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val buyerUserName: String,
    val buyerShopItemId: String
)
