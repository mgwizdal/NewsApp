package com.example.android.newsapp.assemble

import com.example.android.newsapp.mvp.MainPresenter
import com.example.android.newsapp.mvp.contract.MainContract
import com.example.android.newsapp.repository.NewsGateway
import com.example.android.newsapp.repository.RepositoryProvider
import com.example.android.newsapp.usecase.UseCaseFactory

object MainModule {

    fun assemblePresenter(): MainContract.Presenter {
        return providePresenter(provideUseCaseFactory(provideNewsGateway()))
    }

    private fun provideNewsGateway(): NewsGateway {
        return RepositoryProvider.provideNewsRepository()
    }

    private fun provideUseCaseFactory(newsGateway: NewsGateway): UseCaseFactory {
        return UseCaseFactory(newsGateway)
    }

    private fun providePresenter(useCaseFactory: UseCaseFactory): MainContract.Presenter {
        return MainPresenter(useCaseFactory)
    }
}
