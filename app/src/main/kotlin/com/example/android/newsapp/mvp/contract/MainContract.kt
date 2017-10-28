package com.example.android.newsapp.mvp.contract

import com.example.android.newsapp.model.Result
import com.example.android.newsapp.mvp.base.BasePresenter
import com.example.android.newsapp.mvp.base.BaseView


interface MainContract {

    interface View : BaseView {
        fun showNews(news: List<Result>)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getListSize(): Int
        abstract fun provideName(position: Int): String
    }
}
