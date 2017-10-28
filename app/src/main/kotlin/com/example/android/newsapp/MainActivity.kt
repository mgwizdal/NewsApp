package com.example.android.newsapp

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.android.newsapp.assemble.MainModule
import com.example.android.newsapp.databinding.ActivityMainBinding
import com.example.android.newsapp.model.Result
import com.example.android.newsapp.mvp.contract.MainContract

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter, ActivityMainBinding>(), MainContract.View {

    private lateinit var emptyStateTextView: TextView

    private lateinit var queryEditText: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var newsRecyclerView: RecyclerView

    override fun assemblePresenter(): MainContract.Presenter {
        return MainModule.assemblePresenter()
    }

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchButton = binding.searchButton
        newsRecyclerView = binding.list
        queryEditText = binding.searchEditText
        emptyStateTextView = binding.emptyView
    }

    override fun showError(message: String) {

    }

    override fun showNews(news: List<Result>) {
    }

}

