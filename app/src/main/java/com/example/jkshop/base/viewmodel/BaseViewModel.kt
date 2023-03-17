package com.example.jkshop.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jkshop.model.ShopItemEntity
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected val _errorAlert = MutableLiveData<() -> Unit>()
    val errorAlert: LiveData<() -> Unit>
        get() = _errorAlert
}