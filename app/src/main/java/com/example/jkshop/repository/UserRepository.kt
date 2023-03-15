package com.example.jkshop.repository

import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.UserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(private val roomManager: RoomManager) {

    fun findUser(userName: String): Single<String?> {
        return Single.create<String?> {
            try {
                roomManager.getRoomDB().getUserDao().getUser(userName)?.run {
                    it.onSuccess(this)
                } ?: run {
                    it.onError(NullPointerException("User is null"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun userRegister(user: UserEntity): Single<Unit> {
        return Single.create<Unit> {
            try {
                roomManager.getRoomDB().getUserDao().userRegister(user)
                it.onSuccess(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}