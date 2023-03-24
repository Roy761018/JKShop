package com.example.jkshop.util

import androidx.room.TypeConverter
import com.example.jkshop.model.ShopItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 *  Room 專用類別轉換器
 */
abstract class BaseTypeConverter<T>(private val token: TypeToken<T>) {

    private val gson: Gson by lazy {
        Gson()
    }

    @TypeConverter
    fun fromJson(obj: T): String {
        return gson.toJson(obj, token.type)
    }

    @TypeConverter
    fun toJson(jsonString: String): T {
        return gson.fromJson(jsonString, token.type)
    }
}

/**
 *  不同的 TypeConverter 可以在此擴充
 */
class ShopItemEntityTypeConverter: BaseTypeConverter<ArrayList<ShopItemEntity>>(
    object : TypeToken<ArrayList<ShopItemEntity>>(){}
)