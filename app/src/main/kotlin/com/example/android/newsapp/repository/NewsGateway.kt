package com.example.android.newsapp.repository

import com.example.android.newsapp.model.Result
import com.example.android.newsapp.usecase.base.DataCallback


interface NewsGateway {
    fun getNews(dataCallback: DataCallback<List<Result>>)
}
