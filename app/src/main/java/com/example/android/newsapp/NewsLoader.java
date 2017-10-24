package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.newsapp.refactor.model.News;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<News> newslisteners = QueryUtils.fetchNewsData(mUrl);
        return newslisteners;
    }
}
