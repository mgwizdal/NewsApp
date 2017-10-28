package com.example.android.newsapp.refactor.repository.remote;

import com.example.android.newsapp.refactor.model.News;
import com.example.android.newsapp.refactor.repository.NewsGateway;
import com.example.android.newsapp.refactor.repository.remote.api.NewsService;
import com.example.android.newsapp.refactor.usecase.DataCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO Implements remote layer
public class NewsRemoteRepository implements NewsGateway {

    private final NewsService newsService;

    public NewsRemoteRepository(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void getNews(final DataCallback dataCallback) {
        newsService.getNews().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                dataCallback.onSuccess(response.body().getResponse().getResults());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
