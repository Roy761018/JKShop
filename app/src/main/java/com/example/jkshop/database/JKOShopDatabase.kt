package com.example.jkshop.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jkshop.dao.ShopItemDao
import com.example.jkshop.dao.UserDao
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShoppingCartEntity
import com.example.jkshop.model.UserEntity

@Database(entities = [UserEntity::class, ShopItemEntity::class,
    ShoppingCartEntity::class], version = 1)
abstract class JKOShopDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    abstract fun getShopItemDao(): ShopItemDao
}