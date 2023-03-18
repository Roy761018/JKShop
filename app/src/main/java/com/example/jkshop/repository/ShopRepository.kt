package com.example.jkshop.repository

import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShoppingCartEntity
import com.example.jkshop.model.UserEntity
import com.example.jkshop.util.JkShopStaticValue
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

    fun getShopItemDetail(id: String): Single<ShopItemEntity> {
        return Single.create<ShopItemEntity> {
            try {
                roomManager.getRoomDB().getShopItemDao().getShopItemDetail(id)?.run {
                    it.onSuccess(this)
                } ?: run {
                    it.onError(NullPointerException("ShopItem is null"))
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

    fun insertShopCartItem(shopCartItem: ShoppingCartEntity): Single<Unit> {
        return Single.create<Unit> {
            try {
                roomManager.getRoomDB().getShopItemDao().insertItemToShopCart(shopCartItem)
                it.onSuccess(Unit)
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getShoppingCartList(): Single<List<ShopItemEntity>> {
        return Single.create<List<ShopItemEntity>> {
            try {
                val username = JkShopStaticValue.getNowUserName()
                roomManager.getRoomDB().getShopItemDao().getShoppingCartItemList(username)?.run {
                    it.onSuccess(this)
                } ?: run {
                    it.onError(NullPointerException("ShopItem is null"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}