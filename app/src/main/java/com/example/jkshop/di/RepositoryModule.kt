package com.example.jkshop.di

import com.example.jkshop.repository.OrderRepository
import com.example.jkshop.repository.ShopRepository
import com.example.jkshop.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { UserRepository(get()) }
    factory { ShopRepository(get()) }
    factory { OrderRepository(get()) }
}