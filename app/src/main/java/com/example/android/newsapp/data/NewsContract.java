package com.example.android.newsapp.data;

import android.provider.BaseColumns;

/**
 * Created by Maks on 2017-08-22.
 */
public final class NewsContract {
    private NewsContract() {}

    /* Inner class that defines the table contents */
    public static class NewsEntry  implements BaseColumns {
        public static final String TABLE_NAME = "news";
        public static final String _ID = BaseColumns._ID;
        public final static String COLUMN_NEWS_TITLE= "title";
        public final static String COLUMN_NEWS_SECTION= "section";
        public final static String COLUMN_NEWS_TYPE = "type";
        public final static String COLUMN_NEWS_DATE = "date";
        public final static String COLUMN_NEWS_URL = "url";

    }
}
