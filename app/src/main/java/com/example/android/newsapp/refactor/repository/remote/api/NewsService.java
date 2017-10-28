package com.example.android.newsapp.refactor.repository.remote.api;

import com.example.android.newsapp.refactor.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    //FIXME implement the search
    @GET("/search?maxResults=10&order-by=newest&api-key=test&q=")
    Call<News> getNews();
}
