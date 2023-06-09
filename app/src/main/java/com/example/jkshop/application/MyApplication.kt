package com.example.jkshop.application

import android.app.Application
import com.example.jkshop.di.appModules
import com.example.jkshop.di.repositoryModule
import com.example.jkshop.util.JkShopStaticValue
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModules, repositoryModule)
            JkShopStaticValue.initContext(applicationContext)
        }
    }
}