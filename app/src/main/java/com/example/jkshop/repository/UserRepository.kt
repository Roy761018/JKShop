package com.example.jkshop.repository

import com.example.jkshop.base.BaseRepository
import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.UserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(private val roomManager: RoomManager): BaseRepository() {

    fun findUser(userName: String): Single<String?> {
        return createSingle { roomManager.getRoomDB().getUserDao().getUser(userName) }
    }

    fun userRegister(user: UserEntity): Single<Unit> {
        return createSingle { roomManager.getRoomDB().getUserDao().userRegister(user) }
    }
}