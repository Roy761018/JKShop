package com.example.jkshop.repository

import com.example.jkshop.base.BaseRepository
import com.example.jkshop.database.JKOShopDatabase
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.util.JkShopStaticValue
import io.reactivex.Single

class OrderRepository(private val jkoShopDatabase: JKOShopDatabase): BaseRepository() {

    fun orderConfirm(orderEntity: ShopOrderEntity): Single<Unit> {
        return createSingle { jkoShopDatabase.getOrderDao().generateOrder(orderEntity) }
    }

    fun getUserOrderHistory(): Single<List<ShopOrderEntity>> {
        return createSingle {
            val username = JkShopStaticValue.getNowUserName()
            jkoShopDatabase.getOrderDao().getUserOrderHistory(username)
        }
    }

    fun deleteOrderHistory(orderID: String): Single<Unit> {
        return createSingle { jkoShopDatabase.getOrderDao().deleteOrderHistory(orderID) }
    }
}