package com.example.android.newsapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Maks on 2017-08-22.
 */
public final class NewsContract {
    private NewsContract() {
    }
    //To create URI
    public static final String CONTENT_AUTHORITY = "com.example.android.newsapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_NEWS = "news";

    public static class NewsEntry implements BaseColumns {
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS);
        //Table columns
        public static final String TABLE_NAME = "news";
        public static final String _ID = BaseColumns._ID;
        public final static String COLUMN_NEWS_TITLE = "title";
        public final static String COLUMN_NEWS_SECTION = "section";
        public final static String COLUMN_NEWS_TYPE = "type";
        public final static String COLUMN_NEWS_DATE = "date";
        public final static String COLUMN_NEWS_URL = "url";

    }
}
