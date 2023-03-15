package com.example.jkshop.di

import com.example.jkshop.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { UserRepository(get()) }
}