package com.example.android.newsapp.refactor.usecase;

import com.example.android.newsapp.refactor.repository.NewsGateway;

public class UseCaseFactory {

    private final NewsGateway gateway;

    public UseCaseFactory(NewsGateway gateway) {
        this.gateway = gateway;
    }

    public GetNewsUseCase getNewsUseCase(DataCallback callback) {
        return new GetNewsUseCase(gateway, callback);
    }
}
