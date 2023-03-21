package com.example.jkshop.repository

import com.example.jkshop.base.BaseRepository
import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.util.JkShopStaticValue
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OrderRepository(private val roomManager: RoomManager): BaseRepository() {

    fun orderConfirm(orderEntity: ShopOrderEntity): Single<Unit> {
        return createSingle { roomManager.getRoomDB().getOrderDao().generateOrder(orderEntity) }
    }

    fun getUserOrderHistory(): Single<List<ShopOrderEntity>?> {
        return createSingle {
            val username = JkShopStaticValue.getNowUserName()
            roomManager.getRoomDB().getOrderDao().getUserOrderHistory(username)
        }
    }

    fun deleteOrderHistory(orderID: String): Single<Unit> {
        return createSingle { roomManager.getRoomDB().getOrderDao().deleteOrderHistory(orderID) }
    }
}