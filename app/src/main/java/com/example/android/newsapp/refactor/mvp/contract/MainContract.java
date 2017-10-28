package com.example.android.newsapp.refactor.mvp.contract;

import com.example.android.newsapp.refactor.model.Result;
import com.example.android.newsapp.refactor.mvp.base.BasePresenter;
import com.example.android.newsapp.refactor.mvp.base.BaseView;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {
        void showNews(List<Result> news);
    }

    abstract class Presenter extends BasePresenter<View> {

    }
}
