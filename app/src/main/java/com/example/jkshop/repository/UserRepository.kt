package com.example.jkshop.repository

import com.example.jkshop.base.BaseRepository
import com.example.jkshop.database.JKOShopDatabase
import com.example.jkshop.model.UserEntity
import io.reactivex.Single

class UserRepository(private val jkoShopDatabase: JKOShopDatabase): BaseRepository() {

    fun findUser(userName: String): Single<String?> {
        return createSingle { jkoShopDatabase.getUserDao().getUser(userName) }
    }

    fun userRegister(user: UserEntity): Single<Unit> {
        return createSingle { jkoShopDatabase.getUserDao().userRegister(user) }
    }
}