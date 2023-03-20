package com.example.jkshop.layer.shopdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShoppingCartEntity
import com.example.jkshop.repository.ShopRepository
import com.example.jkshop.util.JkShopStaticValue

class JkoShopDetailViewModel(private val shopRepository: ShopRepository): BaseViewModel() {

    private val _getShopItem = MutableLiveData<ShopItemEntity>()
    val getShopItem: LiveData<ShopItemEntity>
        get() = _getShopItem

    private val _isAddSuccess = MutableLiveData<Boolean>()
    val isAddSuccess: LiveData<Boolean>
        get() = _isAddSuccess

    fun getShopDetail(id: String) {
        shopRepository.getShopItemDetail(id).subscribe(
            {
                _getShopItem.value = it
            },
            {
                _errorAlert.value = {
                    getShopDetail(id)
                }
            }).apply {
                compositeDisposable.add(this)
        }
    }

    fun addItemToShopCart() {
        val shopCartEntity = ShoppingCartEntity(
            uid = 0,
            buyerUserName = JkShopStaticValue.getNowUserName(),
            buyerShopItemId = _getShopItem.value?.shopId.toString())
        shopRepository.insertShopCartItem(shopCartEntity).subscribe(
            {
                _isAddSuccess.value = true
            },
            {
                _isAddSuccess.value = false
            }).apply {
            compositeDisposable.add(this)
        }
    }

    fun getShopItem(): ShopItemEntity? {
        return _getShopItem.value
    }
}