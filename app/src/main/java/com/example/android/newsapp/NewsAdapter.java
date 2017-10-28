package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.android.newsapp.refactor.model.News;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> newslisteners) {
        super(context, 0, newslisteners);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_news, parent, false);
        }
        News currentNewsListener = getItem(position);
        return listItemView;
    }

}
