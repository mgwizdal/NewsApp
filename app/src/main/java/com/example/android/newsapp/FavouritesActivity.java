package com.example.android.newsapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.newsapp.data.NewsContract;
import com.example.android.newsapp.data.NewsDbHelper;

/**
 * Created by Maks on 2017-08-22.
 */

public class FavouritesActivity extends AppCompatActivity{
    public NewsDbHelper mDbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        mDbHelper = new NewsDbHelper(this);
        insertNews();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        /*// Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME, null);*/
        String[] projection = {
                NewsContract.NewsEntry._ID,
                NewsContract.NewsEntry.COLUMN_NEWS_TITLE,
                NewsContract.NewsEntry.COLUMN_NEWS_SECTION,
                NewsContract.NewsEntry.COLUMN_NEWS_TYPE,
                NewsContract.NewsEntry.COLUMN_NEWS_DATE,
                NewsContract.NewsEntry.COLUMN_NEWS_URL,
        };
        Cursor cursor =db.query(
                NewsContract.NewsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
        TextView displayView = (TextView) findViewById(R.id.text_view_favourites);
        try {

            displayView.setText("The news table contains " + cursor.getCount() + " news.\n\n");
            displayView.append(NewsContract.NewsEntry._ID + " - " +
                    NewsContract.NewsEntry.COLUMN_NEWS_TITLE + " - " +
                    NewsContract.NewsEntry.COLUMN_NEWS_SECTION + " - " +
                    NewsContract.NewsEntry.COLUMN_NEWS_TYPE + " - " +
                    NewsContract.NewsEntry.COLUMN_NEWS_DATE + " - " +
                    NewsContract.NewsEntry.COLUMN_NEWS_URL  +"\n");

            int idColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_TITLE);
            int sectionColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_SECTION);
            int typeColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_TYPE);
            int dateColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_DATE);
            int urlColumnIndex = cursor.getColumnIndex(NewsContract.NewsEntry.COLUMN_NEWS_URL);

            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(titleColumnIndex);
                String currentSection = cursor.getString(sectionColumnIndex);
                String currentType = cursor.getString(typeColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentUrl = cursor.getString(urlColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentTitle+ " - " +
                        currentSection+ " - " +
                        currentType+ " - " +
                        currentDate+ " - " +
                        currentUrl));
            }
        }
        finally {
            cursor.close();
        }
    }
    private void insertNews() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String news_title = "RAZDWATRZYYY";
        String news_section = "terrier";
        String news_type = "RAZDWATRZYYY";
        String news_date = "12.07.2017";
        String news_url = "https://www.wp.pl/";

        ContentValues values = new ContentValues();
        values.put(NewsContract.NewsEntry.COLUMN_NEWS_TITLE, news_title);
        values.put(NewsContract.NewsEntry.COLUMN_NEWS_SECTION, news_section);
        values.put(NewsContract.NewsEntry.COLUMN_NEWS_TYPE, news_type);
        values.put(NewsContract.NewsEntry.COLUMN_NEWS_DATE, news_date);
        values.put(NewsContract.NewsEntry.COLUMN_NEWS_URL, news_url);

        long newRowId = db.insert(NewsContract.NewsEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving news", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "News saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
