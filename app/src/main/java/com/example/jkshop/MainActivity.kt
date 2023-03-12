package com.example.jkshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jkshop.database.JKOShopDatabase
import com.example.jkshop.manager.JKShopRoomManager
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val jkoJKShopRoomManager: JKShopRoomManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}