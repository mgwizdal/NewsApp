package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import static com.example.android.newsapp.data.NewsContract.*;

/**
 * Created by Maks on 2017-08-22.
 */

public class DetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private Menu menu;
    String LOG_TAG = "DetailsActivity";
    private TextView titleTV;
    private TextView typeTV;
    private TextView sectionTV;
    private TextView dateTV;
    private Button openBrowserButton;
    private String titleString;
    private String urlString;
    private boolean isFavourite;
    private Uri mCurrentNewsUri;
    News currentNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleTV = (TextView) findViewById(R.id.details_title);
        typeTV = (TextView) findViewById(R.id.details_type);
        sectionTV = (TextView) findViewById(R.id.details_section);
        dateTV = (TextView) findViewById(R.id.details_date);
        openBrowserButton = (Button) findViewById(R.id.details_open_button);
        //Get Uri from parent Activity, if it exists
        Intent intent = getIntent();
        mCurrentNewsUri = intent.getData();
        //If it exists, get Uri from parent, if not, get strings from parent
        if (mCurrentNewsUri != null) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            titleString = "";
            titleString = getIntent().getStringExtra("title");
            String typeString = getIntent().getStringExtra("type");
            String sectionString = getIntent().getStringExtra("section");
            String dateString = getIntent().getStringExtra("date");
            urlString = "";
            urlString = getIntent().getStringExtra("url");
            //Create new current News
            currentNews = new News(titleString, sectionString, dateString, urlString, typeString, isFavourite);
            //Check if it in the database, if it is it must be set to favourite
            getLoaderManager().initLoader(1, null, this);
            currentNews.setFavourite(isFavourite);

            titleTV.setText(titleString);
            typeTV.setText(typeString);
            sectionTV.setText(sectionString);
            dateTV.setText(dateString);

        }
        openBrowserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri newsUri = Uri.parse(urlString);
                Intent i = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(i);
            }
        });

    }

    private void insertNews(News news) {
        String news_title = news.getTitle();
        String news_section = news.getSection();
        String news_type = news.getType();
        String news_date = news.getDate();
        String news_url = news.getUrl();
        //Log.i(LOG_TAG, "Current News in insert news: " + currentNews.getTitle() + ", " + currentNews.getSectionName() + ", " + currentNews.getDate() + ", " + currentNews.getType() + ", " + currentNews.getDate() + ", " + currentNews.getUrl());

        ContentValues values = new ContentValues();
        values.put(NewsEntry.COLUMN_NEWS_TITLE, news_title);
        values.put(NewsEntry.COLUMN_NEWS_SECTION, news_section);
        values.put(NewsEntry.COLUMN_NEWS_TYPE, news_type);
        values.put(NewsEntry.COLUMN_NEWS_DATE, news_date);
        values.put(NewsEntry.COLUMN_NEWS_URL, news_url);

        Uri newUri = getContentResolver().insert(NewsEntry.CONTENT_URI, values);
    }

    private void deletePet() {
        // Only perform the delete if this is an existing row.
        if (mCurrentNewsUri != null) {
            // Call the ContentResolver to delete the row at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentNewsUri
            // content URI already identifies the row that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentNewsUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, R.string.no_news_deleted,
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, R.string.news_deleted,
                        Toast.LENGTH_SHORT).show();
            }
        }
        //finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Create menu, inflate, set proper icon if necessary
        getMenuInflater().inflate(R.menu.menu_details, menu);
        if (currentNews.getFavourite()) {
            menu.findItem(R.id.action_set_as_favourite).setIcon(android.R.drawable.star_big_on);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_set_as_favourite:
                //If current news is false, set it as true, turn the star on, insert news into database
                if (!currentNews.getFavourite()) {
                    boolean isFavourite = true;
                    currentNews.setFavourite(isFavourite);
                    item.setIcon(android.R.drawable.star_big_on);
                    //Log.i(LOG_TAG, "Current News in star action: " + currentNews.getTitle() + ", " + currentNews.getSectionName() + ", " + currentNews.getDate() + ", " + currentNews.getType() + ", " + currentNews.getDate() + ", " + currentNews.getUrl());
                    insertNews(currentNews);
                    Toast.makeText(getApplicationContext(), "Article added to favourite list!", Toast.LENGTH_SHORT).show();
                } else {
                    //If current news is true, set it as false, turn the star off, delete news from database
                    boolean isFavourite = false;
                    currentNews.setFavourite(isFavourite);
                    item.setIcon(android.R.drawable.star_off);
                    deletePet();
                    Toast.makeText(getApplicationContext(), "Article not on favourite list anymore.", Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        //If i is set to 0, find row in database with selected Uri, if not, search whole table and find
        // row with selected title
        String selection = null;
        String[] selectionArgs = {""};
        String limit = null;
        Uri uriConnector = mCurrentNewsUri;
        String[] projection = {
                NewsEntry._ID,
                NewsEntry.COLUMN_NEWS_TITLE,
                NewsEntry.COLUMN_NEWS_SECTION,
                NewsEntry.COLUMN_NEWS_TYPE,
                NewsEntry.COLUMN_NEWS_DATE,
                NewsEntry.COLUMN_NEWS_URL
        };
        //
        if (i == 1) {
            selection = NewsEntry.COLUMN_NEWS_TITLE + " =?";
            selectionArgs[0] = titleString;
            limit = "1";
            uriConnector = NewsEntry.CONTENT_URI;
        }

        // This loader will execute the ContentProvider's query method on a background thread
        CursorLoader cursorLoader = new CursorLoader(this,   // Parent activity context
                uriConnector,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                selection,                   // No selection clause
                selectionArgs,                   // No selection arguments
                limit);                  // Default sort order


        return cursorLoader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(NewsEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(NewsEntry.COLUMN_NEWS_TITLE);
            int sectionColumnIndex = cursor.getColumnIndex(NewsEntry.COLUMN_NEWS_SECTION);
            int typeColumnIndex = cursor.getColumnIndex(NewsEntry.COLUMN_NEWS_TYPE);
            int dateColumnIndex = cursor.getColumnIndex(NewsEntry.COLUMN_NEWS_DATE);
            int urlColumnIndex = cursor.getColumnIndex(NewsEntry.COLUMN_NEWS_URL);

            //if there is no id, because news is from main activity, it is necessary to find id and create new Uri
            int idInt = cursor.getInt(idColumnIndex);
            mCurrentNewsUri = ContentUris.withAppendedId(NewsEntry.CONTENT_URI, idInt);
            String titleString = cursor.getString(titleColumnIndex);
            String typeString = cursor.getString(typeColumnIndex);
            String sectionString = cursor.getString(sectionColumnIndex);
            String dateString = cursor.getString(dateColumnIndex);
            urlString = "";
            urlString = cursor.getString(urlColumnIndex);
            boolean isFavourite = true;

            titleTV.setText(titleString);
            typeTV.setText(typeString);
            sectionTV.setText(sectionString);
            dateTV.setText(dateString);
            currentNews = new News(titleString, sectionString, dateString, urlString, typeString, isFavourite);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        titleTV.setText("");
        typeTV.setText("");
        sectionTV.setText("");
        dateTV.setText("");
        urlString = "";

    }
}