package com.example.android.newsapp.mvp.base


abstract class BasePresenter<V : BaseView> {

    protected var view: V? = null

    fun onTakeView(view: V) {
        this.view = view
        onTakenView()
    }

    fun onDropView() {
        this.view = null
    }

    open fun onTakenView() {
        //This method will be implement by children
    }
}
