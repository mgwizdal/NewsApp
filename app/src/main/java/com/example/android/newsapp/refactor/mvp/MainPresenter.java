package com.example.android.newsapp.refactor.mvp;

import com.example.android.newsapp.refactor.model.News;
import com.example.android.newsapp.refactor.model.Result;
import com.example.android.newsapp.refactor.mvp.contract.MainContract;
import com.example.android.newsapp.refactor.usecase.DataCallback;
import com.example.android.newsapp.refactor.usecase.UseCaseFactory;

import java.util.List;

public class MainPresenter extends MainContract.Presenter implements DataCallback<List<Result>> {

    private final UseCaseFactory useCaseFactory;

    public MainPresenter(UseCaseFactory useCaseFactory) {
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void onTakenView() {
        super.onTakenView();
        useCaseFactory.getNewsUseCase(this).execute();
    }

    @Override
    public void onSuccess(List<Result> data) {
        view.showNews(data);
    }

    @Override
    public void onFailure(String errorMessage, int errorCode) {
        view.showError(errorMessage);
    }
}
