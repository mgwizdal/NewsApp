package com.example.android.newsapp.repository

import com.example.android.newsapp.assemble.RemoteModule
import com.example.android.newsapp.repository.remote.NewsRemoteRepository


object RepositoryProvider {

    fun provideNewsRepository(): NewsGateway {
        return NewsRemoteRepository(RemoteModule.assembleService())
    }
}
