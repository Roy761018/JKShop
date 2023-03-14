package com.example.jkshop.manager

import android.content.Context
import androidx.room.Room
import com.example.jkshop.database.JKOShopDatabase

class RoomManager(private val applicationContext: Context) {

    fun getRoomDB(): JKOShopDatabase {
        return Room.databaseBuilder(applicationContext, JKOShopDatabase::class.java, "JKOShop").build()
    }
}