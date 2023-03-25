package com.example.jkshop.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("StaticFieldLeak")
object JkShopStaticValue {

    private const val NOW_USER_NAME = "NOW_USER_NAME"
    private const val INIT_SHOP_LIST = "INIT_SHOP_LIST"

    private lateinit var context: Context

    fun initContext(applicationContext: Context) {
        this.context = applicationContext
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("JK_Shop", Context.MODE_PRIVATE)
    }

    fun setNowUserName(userName: String) {
        getSharedPreferences().edit().putString(NOW_USER_NAME, userName).apply()
    }

    fun getNowUserName(): String {
        return getSharedPreferences().getString(NOW_USER_NAME, "") ?: ""
    }

    fun setNeedInitShopList(isInit: Boolean) {
        getSharedPreferences().edit().putBoolean(INIT_SHOP_LIST, isInit).apply()
    }

    fun isNeedInitShopList(): Boolean {
        return getSharedPreferences().getBoolean(INIT_SHOP_LIST, true)
    }
}