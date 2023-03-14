package com.example.jkshop.di

import com.example.jkshop.login.LoginViewModel
import com.example.jkshop.manager.RoomManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { RoomManager(androidContext()) }

    // ViewModel
    viewModel { LoginViewModel(get()) }

}