package com.example.android.newsapp.usecase

import com.example.android.newsapp.model.Result
import com.example.android.newsapp.repository.NewsGateway
import com.example.android.newsapp.usecase.base.DataCallback

class UseCaseFactory constructor(private val gateway: NewsGateway) {

    fun getNewsUseCase(callback: DataCallback<List<Result>>): GetNewsUseCase = GetNewsUseCase(gateway, callback)
}
