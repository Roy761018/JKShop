package com.example.jkshop.repository

import com.example.jkshop.base.BaseRepository
import com.example.jkshop.manager.RoomManager
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShoppingCartEntity
import com.example.jkshop.model.UserEntity
import com.example.jkshop.util.JkShopStaticValue
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopRepository(private val roomManager: RoomManager): BaseRepository() {

    fun getShopItemList(pageItemCount: Int, offset: Int): Single<List<ShopItemEntity>> {
        return createSingle { roomManager.getRoomDB().getShopItemDao().getShopItemList(pageItemCount, offset) }
    }

    fun getShopItemDetail(id: String): Single<ShopItemEntity> {
        return createSingle { roomManager.getRoomDB().getShopItemDao().getShopItemDetail(id) }
    }

    fun insertShopList(shopList: List<ShopItemEntity>): Single<Unit> {
        return createSingle { roomManager.getRoomDB().getShopItemDao().insertShopItemList(shopList) }
    }

    fun insertShopCartItem(shopCartItem: ShoppingCartEntity): Single<Unit> {
        return createSingle { roomManager.getRoomDB().getShopItemDao().insertItemToShopCart(shopCartItem) }
    }

    fun getShoppingCartList(): Single<List<ShopItemEntity>> {
        return createSingle { val username = JkShopStaticValue.getNowUserName()
            roomManager.getRoomDB().getShopItemDao().getShoppingCartItemList(username)
        }
    }

    fun clearShopCart(): Single<Unit> {
        return createSingle { val username = JkShopStaticValue.getNowUserName()
            roomManager.getRoomDB().getShopItemDao().clearShoppingCartByUserName(username)
        }
    }
}