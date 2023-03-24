package com.example.jkshop.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseRepository {

    protected fun <T> createSingle(dbAction: () -> T): Single<T> {
        return Single.create<T> {
            try {
                dbAction.invoke()?.run {
                    it.onSuccess(this)
                } ?: run {
                    it.onError(NullPointerException("object is null"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                it.onError(e)
            }

        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}