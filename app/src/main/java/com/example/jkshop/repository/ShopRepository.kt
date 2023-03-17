package com.example.jkshop.repository

import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.UserEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopRepository(private val roomManager: RoomManager) {

    fun getShopItemList(): Single<List<ShopItemEntity>> {
        return Single.create<List<ShopItemEntity>> {
            try {
                roomManager.getRoomDB().getShopItemDao().getShopItemList()?.run {
                    it.onSuccess(this)
                } ?: run {
                    it.onError(NullPointerException("List is null"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertShopList(shopList: List<ShopItemEntity>): Single<Unit> {
        return Single.create<Unit> {
            try {
                roomManager.getRoomDB().getShopItemDao().insertShopItemList(shopList)
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