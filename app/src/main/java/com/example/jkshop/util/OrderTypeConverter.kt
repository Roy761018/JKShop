package com.example.jkshop.util

import androidx.room.TypeConverter
import com.example.jkshop.model.ShopItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object OrderTypeConverter {

    @JvmStatic
    @TypeConverter
    fun fromList(list: ArrayList<ShopItemEntity>): String {
        return Gson().toJson(list)
    }

    @JvmStatic
    @TypeConverter
    fun toList(jsonString: String): ArrayList<ShopItemEntity> {
        val type = object : TypeToken<ArrayList<ShopItemEntity>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}