package com.example.jkshop.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jkshop.database.JKOShopDatabase
import com.example.jkshop.manager.RoomManager

class LoginViewModel(private val roomManager: RoomManager): ViewModel() {

    private val db: JKOShopDatabase by lazy {
        roomManager.getRoomDB()
    }

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean>
        get() = _isLoginSuccess

    fun checkUser(userName: String) {
        db.getUserDao().getUser(userName)?.isNotBlank().run {

        } ?: run {

        }
    }
}