package com.example.android.newsapp;

public class News {
    private String mSectionName;
    private String mTitle;
    private String mDate;
    private String mUrl;

    public News(String sectionName, String title, String date, String url) {
        mSectionName = sectionName;
        mTitle = title;
        mDate = date;
        mUrl = url;
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
}
