package com.example.android.newsapp

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.newsapp.databinding.ItemNewsBinding
import com.example.android.newsapp.mvp.contract.MainContract

class NewsRecyclerAdapter(private val presenter: MainContract.Presenter) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemCount(): Int = presenter.getListSize()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        val itemNewBinding: ItemNewsBinding = holder?.biding as ItemNewsBinding
        itemNewBinding.name=presenter.provideName(position)
        itemNewBinding.executePendingBindings()
    }
}
