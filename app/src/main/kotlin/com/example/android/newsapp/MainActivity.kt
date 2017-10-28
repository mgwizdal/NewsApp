package com.example.android.newsapp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View.GONE
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.android.newsapp.assemble.MainModule
import com.example.android.newsapp.databinding.ActivityMainBinding
import com.example.android.newsapp.model.Result
import com.example.android.newsapp.mvp.contract.MainContract

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter, ActivityMainBinding>(), MainContract.View {

    lateinit var newsAdapter: NewsRecyclerAdapter

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
        newsAdapter = NewsRecyclerAdapter(presenter)
        searchButton = binding.searchButton
        newsRecyclerView = binding.recyclerView
        queryEditText = binding.searchEditText
        emptyStateTextView = binding.emptyView

        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)
        newsRecyclerView.adapter = newsAdapter
    }

    override fun showError(message: String) {

    }

    override fun showNews(news: List<Result>) {
        binding.loadingIndicator.visibility = GONE
        newsAdapter.notifyDataSetChanged()
    }

}

