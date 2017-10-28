package com.example.android.newsapp

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.android.newsapp.mvp.base.BasePresenter
import com.example.android.newsapp.mvp.base.BaseView

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>, VB : ViewDataBinding> : AppCompatActivity() {

    lateinit var presenter: P
    lateinit var binding: VB

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun assemblePresenter(): P

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        presenter = assemblePresenter()
        presenter.onTakeView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDropView()
    }
}

