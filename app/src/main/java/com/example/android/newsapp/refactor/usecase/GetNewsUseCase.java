package com.example.android.newsapp.refactor.usecase;

import com.example.android.newsapp.refactor.repository.NewsGateway;

public class GetNewsUseCase implements UseCase {
    private final NewsGateway repository;
    private final DataCallback callback;

    public GetNewsUseCase(NewsGateway repository, DataCallback callback) {
        this.repository = repository;
        this.callback = callback;
    }

    @Override
    public void execute() {
        repository.getNews(callback);
    }
}
