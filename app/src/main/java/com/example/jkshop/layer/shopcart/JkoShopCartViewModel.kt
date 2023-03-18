package com.example.jkshop.layer.shopcart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.repository.ShopRepository

class JkoShopCartViewModel(private val shopRepository: ShopRepository): BaseViewModel() {

    var buyList = arrayListOf<ShopItemEntity>()

    private val _getShoppingCartList = MutableLiveData<List<ShopItemEntity>>()
    val getShoppingCartList: LiveData<List<ShopItemEntity>>
        get() = _getShoppingCartList

    private val _isBtnEnable = MutableLiveData<Boolean>()
    val isBtnEnable: LiveData<Boolean>
        get() = _isBtnEnable

    fun checkButtonEnable() {
        _isBtnEnable.value = buyList.isNotEmpty()
    }

    fun getShoppingCartList() {
        shopRepository.getShoppingCartList().subscribe(
            {
                _getShoppingCartList.value = it
            },
            {
                _errorAlert.value = {
                    getShoppingCartList()
                }
            }).apply {
                compositeDisposable.add(this)
        }
    }
}