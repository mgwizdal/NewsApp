package com.example.android.newsapp;

public class News {
    private String mSectionName;
    private String mTitle;
    private String mDate;
    private String mUrl;
    private String mType;
    private boolean mFavourite;


    public News( String title, String sectionName, String date, String url, String type, boolean favourite) {
        mSectionName = sectionName;
        mTitle = title;
        mDate = date;
        mUrl = url;
        mType = type;
        mFavourite = favourite;
    }

    public String getSectionName() {
        return mSectionName;
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

    public void setFavourite() {
        if (mFavourite == false) {
            mFavourite = true;
        } else{
            mFavourite = false;
        }
    }

}
