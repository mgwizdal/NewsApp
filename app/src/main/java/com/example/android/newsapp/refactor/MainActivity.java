package com.example.android.newsapp.refactor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.newsapp.R;
import com.example.android.newsapp.databinding.ActivityMainBinding;
import com.example.android.newsapp.refactor.assemble.MainModule;
import com.example.android.newsapp.refactor.model.Result;
import com.example.android.newsapp.refactor.mvp.contract.MainContract;

import java.util.List;

public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter, ActivityMainBinding> implements MainContract.View {

    private TextView emptyStateTextView;
    private EditText queryEditText;
    private ImageButton searchButton;
    private RecyclerView newsRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainContract.Presenter assemblePresenter() {
        return new MainModule().getPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchButton = getBinding().searchButton;
        newsRecyclerView = getBinding().recyclerView;
        queryEditText = getBinding().searchEditText;
        emptyStateTextView = getBinding().emptyView;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showNews(List<Result> news) {

    }
}
