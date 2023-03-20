package com.example.jkshop.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["order_id"], unique = true)])
data class ShopOrderEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "order_id")val orderID: String,
    val buyerUsername: String,
    val shopItemEntityList: ArrayList<ShopItemEntity>,
    val orderCreateTime: String,
    val orderPrice: Int
)