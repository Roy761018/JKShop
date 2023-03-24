package com.example.jkshop.layer.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.ShopItemEntity
import com.example.jkshop.model.ShopOrderEntity
import com.example.jkshop.repository.OrderRepository
import com.example.jkshop.repository.ShopRepository
import com.example.jkshop.util.JkShopStaticValue
import java.text.SimpleDateFormat
import java.util.*

class JkoShopOrderConfirmViewModel(private val orderRepository: OrderRepository,
                                   private val shopRepository: ShopRepository): BaseViewModel() {

    private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.TAIWAN)

    var orderList = arrayListOf<ShopItemEntity>()
    var orderTotalPrice = 0

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _setOrderPriceView = MutableLiveData<Int>()
    val setOrderPriceView: LiveData<Int>
        get() = _setOrderPriceView

    fun setOrderPrice(price: Int) {
        orderTotalPrice += price
        _setOrderPriceView.value = orderTotalPrice
    }

    fun orderConfirm() {
        val orderEntity = ShopOrderEntity(uid = 0,
            orderID = UUID.randomUUID().toString(),
            buyerUsername = JkShopStaticValue.getNowUserName(),
            shopItemEntityList = orderList,
            orderCreateTime = simpleDateFormat.format(Date()),
            orderPrice = orderTotalPrice)
        orderRepository.orderConfirm(orderEntity).subscribe(
            {
                _isSuccess.value = true
            },
            {
                _errorAlert.value = {
                    orderConfirm()
                }
            }).apply {
                compositeDisposable.add(this)
        }
    }

    fun clearShopCart() {
        shopRepository.clearShopCart().subscribe(
            {

            },
            {

            }).apply {
            compositeDisposable.add(this)
        }
    }
}