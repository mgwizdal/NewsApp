package com.example.android.newsapp.refactor.repository;

import com.example.android.newsapp.refactor.assemble.RemoteModule;
import com.example.android.newsapp.refactor.repository.remote.NewsRemoteRepository;

public class RepositoryProvider {

    public static NewsGateway provideNewsRepository() {
        return new NewsRemoteRepository(RemoteModule.getService());
    }
}
