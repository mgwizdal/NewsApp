package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String NEWS_JSON = "http://content.guardianapis.com/search?maxResults=10&order-by=newest&api-key=test&q=";
    private static final int LOADER_ID = 1;
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private EditText mQueryEditText;
    android.app.LoaderManager loaderManager;
    private ImageButton searchButton;
    private String mQuery = "";
    ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loaderManager = getLoaderManager();
        searchButton = (ImageButton) findViewById(R.id.search_button);
        newsListView = (ListView) findViewById(R.id.list);
        mQueryEditText = (EditText) findViewById(R.id.search_edit_text);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);
        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(mAdapter);
        Parcelable state = newsListView.onSaveInstanceState();
        newsListView.onRestoreInstanceState(state);
        if(mQueryEditText.getText().toString().equals("")){
            search();
        }
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuery = "";
                mQuery = mQueryEditText.getText().toString();
                search();
            }
        });
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });
    }

    public void search() {
        if (isOnline()) {
            newsListView.setAdapter(mAdapter);
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
            loaderManager.restartLoader(LOADER_ID, null, this);
            if(mQuery.equals("")){
                mEmptyStateTextView.setText(R.string.null_searching);
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);
            }
        } else {
            newsListView.setAdapter(null);
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.internet_error);
        }
    }

    public boolean isOnline() {
        ConnectivityManager connectivityMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMan.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        String finalUrl = "";
        if (mQuery != null && mQuery != "") {
            finalUrl = NEWS_JSON + mQuery;

        } else {
            String defaultQuery = "Poland";        //By default - it searches for Poland related articles, my country.
            finalUrl = NEWS_JSON + defaultQuery;
        }
        return new NewsLoader(this, finalUrl);
    }
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.null_searching);
        mAdapter.clear();
        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
