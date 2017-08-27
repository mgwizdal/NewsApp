package com.example.android.newsapp;

public class News {
    private String mSection;
    private String mTitle;
    private String mDate;
    private String mUrl;
    private String mType;
    private boolean mFavourite;

    public News(String title, String section, String date, String url, String type, boolean favourite) {
        mSection = section;
        mTitle = title;
        mDate = date;
        mUrl = url;
        mType = type;
        mFavourite = favourite;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getType() {
        return mType;
    }

    public boolean getFavourite() {
        return mFavourite;
    }

    public void setFavourite(boolean isFavourite) {
        mFavourite = isFavourite;
        return;
    }

}
