package com.example.android.newsapp.refactor.mvp.base;

public abstract class BasePresenter<V extends BaseView> {

    protected V view;

    public final void onTakeView(V view) {
        this.view = view;
        onTakenView();
    }

    public final void onDropView() {
        this.view = null;
    }

    public void onTakenView() {
        //This method will be implement by children
    }
}
