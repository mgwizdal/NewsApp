package com.example.android.newsapp.usecase

import com.example.android.newsapp.model.Result
import com.example.android.newsapp.repository.NewsGateway
import com.example.android.newsapp.usecase.base.DataCallback
import com.example.android.newsapp.usecase.base.UseCase

class GetNewsUseCase(val repository: NewsGateway, val callback: DataCallback<List<Result>>) : UseCase {
    override fun execute() = repository.getNews(callback)
}
