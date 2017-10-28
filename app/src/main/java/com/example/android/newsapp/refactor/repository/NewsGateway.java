package com.example.android.newsapp.refactor.repository;

import com.example.android.newsapp.refactor.usecase.DataCallback;

public interface NewsGateway {
    void getNews(DataCallback dataCallback);
}
