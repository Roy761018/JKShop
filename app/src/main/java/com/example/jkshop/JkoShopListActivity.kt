package com.example.jkshop

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jkshop.manager.JKShopRoomManager
import org.koin.android.ext.android.inject

class JkoShopListActivity: AppCompatActivity() {

    private val jkoJKShopRoomManager: JKShopRoomManager by inject()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}