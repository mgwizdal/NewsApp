package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.android.newsapp.data.NewsContract.NewsEntry;


/**
 * Created by Maks on 2017-08-22.
 */

public class FavouritesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int NEWS_LOADER = 0;
    NewsCursorAdapter mCursorAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        //Create ListView and EmptyView
        ListView favouritesListView = (ListView) findViewById(R.id.list_favourites);
        View emptyView = findViewById(R.id.empty_view_favourites);
        favouritesListView.setEmptyView(emptyView);

        mCursorAdapter = new NewsCursorAdapter(this, null);
        favouritesListView.setAdapter(mCursorAdapter);
        favouritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(FavouritesActivity.this, DetailsActivity.class);
                Uri currentNewsUri = ContentUris.withAppendedId(NewsEntry.CONTENT_URI, id);
                intent.setData(currentNewsUri);
                startActivity(intent);
            }
        });
        getLoaderManager().initLoader(NEWS_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                NewsEntry._ID,
                NewsEntry.COLUMN_NEWS_TITLE,
                NewsEntry.COLUMN_NEWS_SECTION,
                NewsEntry.COLUMN_NEWS_DATE
        };
        return new CursorLoader(this, NewsEntry.CONTENT_URI, projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
