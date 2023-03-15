package com.example.jkshop.layer.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jkshop.base.viewmodel.BaseViewModel
import com.example.jkshop.model.UserEntity
import com.example.jkshop.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository): BaseViewModel() {

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean>
        get() = _isLoginSuccess

    private val _isUserNotExist = MutableLiveData<Unit>()
    val isUserNotExist: LiveData<Unit>
        get() = _isUserNotExist

    fun checkUserExist(userName: String) {
        userRepository.findUser(userName).subscribe(
            {
                _isLoginSuccess.value = true
            },
            {
                _isUserNotExist.value = Unit
            }).apply {
            compositeDisposable.add(this)
        }
    }

    fun userRegister(user: UserEntity) {
        userRepository.userRegister(user).subscribe(
            {
                _isLoginSuccess.value = true
            },
            {
                _isLoginSuccess.value = false
            }).apply {
                compositeDisposable.add(this)
            }
    }
}