package com.example.android.newsapp.usecase.base


interface DataCallback<T> {

    fun onSuccess(data: T)

    fun onFailure(errorMessage: String, errorCode: Int)
}
