package com.example.jkshop.layer.shoplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.repository.ShopRepository
import com.example.jkshop.util.JkShopStaticValue

class JkoShopListViewModel(private val shopRepository: ShopRepository): BaseViewModel() {

    var currentPage = 0
    var pageItemCount = 15

    private val _getShopList = MutableLiveData<List<ShopItemEntity>>()
    val getShopList: LiveData<List<ShopItemEntity>>
        get() = _getShopList

    fun getShopList() {
        shopRepository.getShopItemList(pageItemCount = pageItemCount, offset = currentPage * pageItemCount).subscribe(
            {
                if (it.isNotEmpty()) {
                    _getShopList.value = it
                } else {
                    // 做一個 swipeRefresh
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

    fun initDefaultShopList() {
        val defaultShopList = generateShopList()
        shopRepository.insertShopList(defaultShopList).subscribe(
            {
                JkShopStaticValue.setInitShopList(true)
                _getShopList.value = defaultShopList
            },
            {
                _errorAlert.value = {
                    initDefaultShopList()
                }
            }).apply {
            compositeDisposable.add(this)
        }
    }

    private fun generateShopList(): List<ShopItemEntity> {
        return mutableListOf<ShopItemEntity>().apply {
            for (i in 0..100) {
                this.add(ShopItemEntity(
                    shopId = "A100$i",
                    name = "商品名稱$i",
                    description = "這是商品名稱$i" + "的說明",
                    price = (0.. 1000).random(),
                    createTime = "2023/3/1"
                ))
            }
        }
    }
}