package com.example.jkshop.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jkshop.dao.OrderDao
import com.example.jkshop.dao.ShopItemDao
import com.example.jkshop.dao.UserDao
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.model.ShoppingCartEntity
import com.example.jkshop.model.UserEntity
import com.example.jkshop.util.ShopItemEntityTypeConverter

@Database(entities = [UserEntity::class, ShopItemEntity::class,
    ShoppingCartEntity::class, ShopOrderEntity::class], version = 1)
@TypeConverters(ShopItemEntityTypeConverter::class)
abstract class JKOShopDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    abstract fun getShopItemDao(): ShopItemDao

    abstract fun getOrderDao(): OrderDao
}