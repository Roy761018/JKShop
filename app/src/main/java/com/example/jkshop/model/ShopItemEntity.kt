package com.example.jkshop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["shop_id"], unique = true)])
data class ShopItemEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "shop_id")val shopId: String,
    val name: String,
    val description: String,
    val price: Int = 0,
    val createTime: String,
    val img: String? = null
)
