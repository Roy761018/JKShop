package com.example.jkshop.di

import com.example.jkshop.manager.JKShopRoomManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModules = module {

    single { JKShopRoomManager(androidContext()) }

}