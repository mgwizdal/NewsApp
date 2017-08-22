package com.example.android.newsapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.android.newsapp.data.NewsContract;
import com.example.android.newsapp.data.NewsDbHelper;

/**
 * Created by Maks on 2017-08-22.
 */

public class DetailsActivity extends AppCompatActivity {
    private Menu menu;
    String LOG_TAG = "DetailsActivity";
    TextView titleTV;
    TextView typeTV;
    TextView sectionTV;
    TextView dateTV;
    Button favouriteButton;
    Button openBrowserButton;

    String titleString;
    String typeString;
    String sectionString;
    String dateString;
    String urlString;
    boolean isFavourite;
    News currentNews;
    NewsDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        titleTV = (TextView) findViewById(R.id.details_title);
        typeTV = (TextView) findViewById(R.id.details_type);
        sectionTV = (TextView) findViewById(R.id.details_section);
        dateTV = (TextView) findViewById(R.id.details_date);
        favouriteButton = (Button) findViewById(R.id.details_favourite_button);
        openBrowserButton = (Button) findViewById(R.id.details_open_button);

        titleString = getIntent().getStringExtra("title");
        typeString = getIntent().getStringExtra("type");
        sectionString = getIntent().getStringExtra("section");
        dateString = getIntent().getStringExtra("date");
        urlString = getIntent().getStringExtra("url");
        isFavourite = getIntent().getBooleanExtra("favourite", isFavourite);

        titleTV.setText(titleString);
        typeTV.setText(typeString);
        sectionTV.setText(sectionString);
        dateTV.setText(dateString);
        currentNews = new News(titleString, sectionString, dateString, urlString, typeString, isFavourite);


        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNews.setFavourite();
                Toast.makeText(getApplicationContext(), "IS IT FAVOURITE?: " + currentNews.getFavourite(), Toast.LENGTH_SHORT).show();
                if(currentNews.getFavourite()){
                    insertNews(currentNews);
                }

            }
        });
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
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String news_title = news.getTitle();
        String news_section = news.getSectionName();
        String news_type = news.getType();
        String news_date = news.getDate();
        String news_url = news.getUrl();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_set_as_favourite:
                currentNews.setFavourite();
                if(currentNews.getFavourite()) {
                    item.setIcon(android.R.drawable.star_big_on);
                }else{
                    item.setIcon(android.R.drawable.star_off);
                }
                Toast.makeText(getApplicationContext(), "IS IT FAVOURITE?: " + currentNews.getFavourite(), Toast.LENGTH_SHORT).show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}