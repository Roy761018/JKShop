package com.example.jkshop.repository

import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.model.UserEntity
import com.example.jkshop.util.JkShopStaticValue
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OrderRepository(private val roomManager: RoomManager) {

    fun orderConfirm(orderEntity: ShopOrderEntity): Single<Unit> {
        return Single.create<Unit> {
            try {
                roomManager.getRoomDB().getOrderDao().generateOrder(orderEntity)
                it.onSuccess(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserOrder(): Single<List<ShopOrderEntity>> {
        return Single.create<List<ShopOrderEntity>> {
            try {
                val username = JkShopStaticValue.getNowUserName()
                roomManager.getRoomDB().getOrderDao().getUserOrder(username)?.run {
                    it.onSuccess(this)
                } ?: run {
                    it.onError(NullPointerException("Order is null"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteOrderHistory(orderID: String): Single<Unit> {
        return Single.create<Unit> {
            try {
                roomManager.getRoomDB().getOrderDao().deleteOrderHistory(orderID)
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