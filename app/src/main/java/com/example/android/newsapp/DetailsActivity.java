package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Maks on 2017-08-22.
 */

public class DetailsActivity extends AppCompatActivity {
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
            public void onClick(View view){
                currentNews.setFavourite();
                Toast.makeText(view.getContext(),"IS IT FAVOURITE?: "+currentNews.getFavourite(), Toast.LENGTH_SHORT).show();

            }
        });
        openBrowserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Uri newsUri = Uri.parse(urlString);
                Intent i = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(i);
            }
        });

    }
}