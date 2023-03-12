package com.example.jkshop.manager

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class JKShopRoomManager(private val applicationContext: Context) {

    fun init() {

    }

    fun getRoomDB(roomDatabase: RoomDatabase): RoomDatabase {
        return Room.databaseBuilder(applicationContext, roomDatabase.javaClass, "JKOShop").build()
    }
}