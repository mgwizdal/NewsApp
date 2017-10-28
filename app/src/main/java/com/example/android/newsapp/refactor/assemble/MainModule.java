package com.example.android.newsapp.refactor.assemble;

import com.example.android.newsapp.refactor.mvp.MainPresenter;
import com.example.android.newsapp.refactor.mvp.contract.MainContract;
import com.example.android.newsapp.refactor.repository.NewsGateway;
import com.example.android.newsapp.refactor.repository.RepositoryProvider;
import com.example.android.newsapp.refactor.usecase.UseCaseFactory;

public class MainModule {

    private MainContract.Presenter presenter;

    public MainContract.Presenter getPresenter() {
        if (presenter == null) {
            presenter = assemblePresenter();
        }
        return presenter;
    }

    private NewsGateway provideNewsGateway() {
        return RepositoryProvider.provideNewsRepository();
    }

    private UseCaseFactory provideUseCaseFactory(NewsGateway newsGateway) {
        return new UseCaseFactory(newsGateway);
    }

    private MainContract.Presenter providePresenter(UseCaseFactory useCaseFactory) {
        return new MainPresenter(useCaseFactory);
    }

    private MainContract.Presenter assemblePresenter() {
        return providePresenter(provideUseCaseFactory(provideNewsGateway()));
    }
}
