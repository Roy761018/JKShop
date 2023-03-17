package com.example.jkshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItemEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val name: String,
    val description: String,
    val price: Int = 0,
    val createTime: String,
    val img: String
)
