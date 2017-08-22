package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                    R.layout.list_item, parent, false);
        }
        News currentNewsListener = getItem(position);
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentNewsListener.getTitle());
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        sectionView.setText(currentNewsListener.getSectionName());
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(currentNewsListener.getDate());
        return listItemView;
    }

}
