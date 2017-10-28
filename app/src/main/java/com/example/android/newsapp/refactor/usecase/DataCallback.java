package com.example.android.newsapp.refactor.usecase;

public interface DataCallback<T> {

    void onSuccess(T data);

    void onFailure(String errorMessage, int errorCode);
}
