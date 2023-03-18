package com.example.jkshop.layer.shopdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.repository.ShopRepository

class JkoShopDetailViewModel(private val shopRepository: ShopRepository): BaseViewModel() {

    private val _getShopItem = MutableLiveData<ShopItemEntity>()
    val getShopItem: LiveData<ShopItemEntity>
        get() = _getShopItem

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
}