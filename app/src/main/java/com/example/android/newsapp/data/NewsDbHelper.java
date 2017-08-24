package com.example.android.newsapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maks on 2017-08-22.
 */

public class NewsDbHelper extends SQLiteOpenHelper {

    //public static final String LOG_TAG = NewsDbHelper.class.getSimpleName();
    // New database
    private static final String DATABASE_NAME = "favourites.db";
    // Version
    private static final int DATABASE_VERSION = 1;

    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //String query
        String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " (" +
                NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_TITLE + " TEXT NOT NULL, " +
                NewsContract.NewsEntry.COLUMN_NEWS_SECTION + " TEXT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_TYPE + " TEXT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_DATE + " TEXT, " +
                NewsContract.NewsEntry.COLUMN_NEWS_URL + " TEXT " +
                " CONSTRAINT title_unique UNIQUE (" + NewsContract.NewsEntry.COLUMN_NEWS_TITLE + "));";
        //Execute SQL query
        db.execSQL(SQL_CREATE_NEWS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}