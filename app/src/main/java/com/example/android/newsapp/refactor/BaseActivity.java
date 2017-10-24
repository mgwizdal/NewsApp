package com.example.android.newsapp.refactor;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.android.newsapp.refactor.mvp.base.BasePresenter;
import com.example.android.newsapp.refactor.mvp.base.BaseView;

public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>, VB extends ViewDataBinding> extends AppCompatActivity {

    private P presenter;
    private V view;
    private VB binding;

    @LayoutRes
    public abstract int getLayoutId();

    public abstract P assemblePresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        presenter = assemblePresenter();
        presenter.onTakeView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDropView();
    }

    public VB getBinding() {
        return binding;
    }

    public P getPresenter() {
        return presenter;
    }
}

