package com.example.android.newsapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.android.newsapp.data.NewsContract.NewsEntry;


public class NewsProvider extends ContentProvider {

    public static final String LOG_TAG = NewsProvider.class.getSimpleName();

    private static final int NEWS = 100;
    private static final int NEWS_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS, NEWS);
        sUriMatcher.addURI(NewsContract.CONTENT_AUTHORITY, NewsContract.PATH_NEWS + "/#", NEWS_ID);
    }

    private NewsDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new NewsDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                cursor = database.query(NewsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case NEWS_ID:
                selection = NewsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(NewsEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                return insertNews(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertNews(Uri uri, ContentValues values) {
        String name = values.getAsString(NewsEntry.COLUMN_NEWS_TITLE);
        if (name == null) {
            throw new IllegalArgumentException("News requires a title");
        }
        String section = values.getAsString(NewsEntry.COLUMN_NEWS_SECTION);
        if (section == null) {
            throw new IllegalArgumentException("News requires a section");
        }
        String type = values.getAsString(NewsEntry.COLUMN_NEWS_TYPE);
        if (type == null) {
            throw new IllegalArgumentException("News requires a type");
        }
        String date = values.getAsString(NewsEntry.COLUMN_NEWS_DATE);
        if (date == null) {
            throw new IllegalArgumentException("News requires a date");
        }
        String url = values.getAsString(NewsEntry.COLUMN_NEWS_URL);
        if (url == null) {
            throw new IllegalArgumentException("News requires a url");
        }
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(NewsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                rowsDeleted = database.delete(NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case NEWS_ID:
                selection = NewsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(NewsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                return NewsEntry.CONTENT_LIST_TYPE;
            case NEWS_ID:
                return NewsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
