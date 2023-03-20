package com.example.jkshop.layer.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.repository.OrderRepository
import com.example.jkshop.repository.ShopRepository
import com.example.jkshop.util.JkShopStaticValue
import java.util.UUID

class MyInfoViewModel(private val orderRepository: OrderRepository): BaseViewModel() {

    private val _setOrderHistoryView = MutableLiveData<List<ShopOrderEntity>>()
    val setOrderHistoryView: LiveData<List<ShopOrderEntity>>
        get() = _setOrderHistoryView

    private val _isDeleteSuccess = MutableLiveData<Boolean>()
    val isDeleteSuccess: LiveData<Boolean>
        get() = _isDeleteSuccess

    fun getOrderHistory() {
        orderRepository.getUserOrder().subscribe(
            {
                _setOrderHistoryView.value = it
            },
            {
                _errorAlert.value = {
                    getOrderHistory()
                }
            }).apply {
                compositeDisposable.add(this)
        }
    }

    fun deleteOrderHistory(orderID: String) {
        orderRepository.deleteOrderHistory(orderID).flatMap {
            orderRepository.getUserOrder()
        }.subscribe(
            {
                _isDeleteSuccess.value = true
                _setOrderHistoryView.value = it
            },
            {
                _isDeleteSuccess.value = false
            }).apply {
            compositeDisposable.add(this)
        }
    }
}