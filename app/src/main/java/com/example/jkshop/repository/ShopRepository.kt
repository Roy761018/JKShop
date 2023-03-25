package com.example.jkshop.repository

import com.example.jkshop.base.BaseRepository
import com.example.jkshop.database.JKOShopDatabase
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShoppingCartEntity
import com.example.jkshop.util.JkShopStaticValue
import io.reactivex.Single

class ShopRepository(private val jkoShopDatabase: JKOShopDatabase): BaseRepository() {

    fun getShopItemList(pageItemCount: Int, offset: Int): Single<List<ShopItemEntity>> {
        return createSingle { jkoShopDatabase.getShopItemDao().getShopItemList(pageItemCount, offset) }
    }

    fun getShopItemDetail(id: String): Single<ShopItemEntity> {
        return createSingle { jkoShopDatabase.getShopItemDao().getShopItemDetail(id) }
    }

    fun insertShopList(shopList: List<ShopItemEntity>): Single<Unit> {
        return createSingle { jkoShopDatabase.getShopItemDao().insertShopItemList(shopList) }
    }

    fun insertShopCartItem(shopCartItem: ShoppingCartEntity): Single<Unit> {
        return createSingle { jkoShopDatabase.getShopItemDao().insertItemToShopCart(shopCartItem) }
    }

    fun getShoppingCartList(): Single<List<ShopItemEntity>> {
        return createSingle { val username = JkShopStaticValue.getNowUserName()
            jkoShopDatabase.getShopItemDao().getShoppingCartItemList(username)
        }
    }

    fun clearShopCart(): Single<Unit> {
        return createSingle { val username = JkShopStaticValue.getNowUserName()
            jkoShopDatabase.getShopItemDao().clearShoppingCartByUserName(username)
        }
    }
}