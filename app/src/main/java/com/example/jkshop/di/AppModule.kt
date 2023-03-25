package com.example.jkshop.di

import androidx.room.Room
import com.example.jkshop.database.JKOShopDatabase
import com.example.jkshop.layer.login.LoginViewModel
import com.example.jkshop.layer.my.MyInfoViewModel
import com.example.jkshop.layer.order.JkoShopOrderConfirmViewModel
import com.example.jkshop.layer.shopcart.JkoShopCartViewModel
import com.example.jkshop.layer.shopdetail.JkoShopDetailViewModel
import com.example.jkshop.layer.shoplist.JkoShopListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    // RoomDataBase 需要被做成 Singleton
    single { Room.databaseBuilder(androidContext(), JKOShopDatabase::class.java, "JKOShop").build() }

    // ViewModel
    viewModel { LoginViewModel(get()) }
    viewModel { JkoShopListViewModel(get()) }
    viewModel { JkoShopDetailViewModel(get()) }
    viewModel { JkoShopCartViewModel(get()) }
    viewModel { JkoShopOrderConfirmViewModel(get(), get()) }
    viewModel { MyInfoViewModel(get()) }

}