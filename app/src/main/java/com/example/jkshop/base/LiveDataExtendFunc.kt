package com.example.jkshop.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData

fun <T> AppCompatActivity.observeLiveData(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(this) {
        action.invoke(it)
    }
}