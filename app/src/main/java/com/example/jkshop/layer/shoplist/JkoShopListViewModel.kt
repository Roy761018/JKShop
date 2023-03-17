package com.example.jkshop.layer.shoplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.repository.ShopRepository

class JkoShopListViewModel(private val shopRepository: ShopRepository): BaseViewModel() {

    private val _getShopList = MutableLiveData<List<ShopItemEntity>>()
    val getShopList: LiveData<List<ShopItemEntity>>
        get() = _getShopList

    fun getShopList() {
        shopRepository.getShopItemList().subscribe(
            {
                if (it.isNotEmpty()) {
                    _getShopList.value = it
                } else {
                    initDefaultShopList(generateShopList())
                }
            },
            {
                _errorAlert.value = {
                    getShopList()
                }
            }).apply {
                compositeDisposable.add(this)
        }
    }

    private fun initDefaultShopList(defaultShopList: List<ShopItemEntity>) {
        shopRepository.insertShopList(defaultShopList).subscribe(
            {
                _getShopList.value = defaultShopList
            },
            {
                _errorAlert.value = {
                    initDefaultShopList(defaultShopList)
                }
            }).apply {
            compositeDisposable.add(this)
        }
    }

    private fun generateShopList(): List<ShopItemEntity> {
        return mutableListOf<ShopItemEntity>().apply {
            for (i in 0..50) {
                this.add(ShopItemEntity(
                    name = "商品名稱$i",
                    description = "這是商品名稱$i" + "的說明",
                    price = 100,
                    createTime = "2023/3/1",
                    img = ""
                ))
            }
        }
    }
}