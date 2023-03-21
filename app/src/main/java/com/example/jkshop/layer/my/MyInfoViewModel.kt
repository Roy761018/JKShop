package com.example.jkshop.layer.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.repository.OrderRepository

class MyInfoViewModel(private val orderRepository: OrderRepository): BaseViewModel() {

    private val _setOrderHistoryView = MutableLiveData<List<ShopOrderEntity>>()
    val setOrderHistoryView: LiveData<List<ShopOrderEntity>>
        get() = _setOrderHistoryView

    private val _isDeleteSuccess = MutableLiveData<Boolean>()
    val isDeleteSuccess: LiveData<Boolean>
        get() = _isDeleteSuccess

    fun getOrderHistory() {
        orderRepository.getUserOrderHistory().subscribe(
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
            orderRepository.getUserOrderHistory()
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