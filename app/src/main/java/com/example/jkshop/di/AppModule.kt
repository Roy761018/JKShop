package com.example.jkshop.di

import com.example.jkshop.layer.login.LoginViewModel
import com.example.jkshop.layer.order.JkoShopOrderConfirmViewModel
import com.example.jkshop.layer.shopcart.JkoShopCartViewModel
import com.example.jkshop.layer.shopdetail.JkoShopDetailViewModel
import com.example.jkshop.layer.shoplist.JkoShopListViewModel
import com.example.jkshop.manager.RoomManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { RoomManager(androidContext()) }

    // ViewModel
    viewModel { LoginViewModel(get()) }
    viewModel { JkoShopListViewModel(get()) }
    viewModel { JkoShopDetailViewModel(get()) }
    viewModel { JkoShopCartViewModel(get()) }
    viewModel { JkoShopOrderConfirmViewModel(get(), get()) }

}