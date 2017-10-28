package com.example.android.newsapp.mvp


import com.example.android.newsapp.model.Result
import com.example.android.newsapp.mvp.contract.MainContract
import com.example.android.newsapp.usecase.UseCaseFactory
import com.example.android.newsapp.usecase.base.DataCallback


class MainPresenter(private val useCaseFactory: UseCaseFactory) : MainContract.Presenter(), DataCallback<List<Result>> {
    private var result: List<Result> = ArrayList<Result>()

    override fun onTakenView() {
        super.onTakenView()
        useCaseFactory.getNewsUseCase(this).execute()
    }

    override fun onSuccess(data: List<Result>) {
        this.result = data
        view?.showNews(data)
    }

    override fun onFailure(errorMessage: String, errorCode: Int) {
        view?.showError(errorMessage)
    }

    override fun getListSize(): Int = result.size

    override fun provideName(position: Int): String = result[position].webTitle
}
