package com.example.android.newsapp.repository.api

import com.example.android.newsapp.model.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {

    @GET("/search?maxResults=10&order-by=newest&api-key=test&q=")
    fun getNews(): Call<News>
}
