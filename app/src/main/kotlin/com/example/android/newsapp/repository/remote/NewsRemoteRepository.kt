package com.example.android.newsapp.repository.remote

import com.example.android.newsapp.model.News
import com.example.android.newsapp.model.Result
import com.example.android.newsapp.repository.NewsGateway
import com.example.android.newsapp.repository.api.NewsService
import com.example.android.newsapp.usecase.base.DataCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRemoteRepository(private val service: NewsService) : NewsGateway {

    override fun getNews(dataCallback: DataCallback<List<Result>>) {
        service.getNews().enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                dataCallback.onSuccess(response.body()?.response?.results!!)
            }

            override fun onFailure(call: Call<News>, throwable: Throwable) {
            }
        })
    }
}
