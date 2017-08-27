package com.example.android.newsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 1;

    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " (" +
                NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_TITLE + " TEXT NOT NULL, " +
                NewsContract.NewsEntry.COLUMN_NEWS_SECTION + " TEXT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_TYPE + " TEXT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_DATE + " TEXT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_URL + " TEXT " +
                " CONSTRAINT title_unique UNIQUE (" + NewsContract.NewsEntry.COLUMN_NEWS_TITLE + "));";
        db.execSQL(SQL_CREATE_NEWS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}