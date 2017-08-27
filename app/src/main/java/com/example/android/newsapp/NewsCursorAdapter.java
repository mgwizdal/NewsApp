package com.example.android.newsapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static com.example.android.newsapp.data.NewsContract.*;


public class NewsCursorAdapter extends CursorAdapter {

    public NewsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView sectionTextView  = (TextView) view.findViewById(R.id.section);
        TextView dateTextView  = (TextView) view.findViewById(R.id.date);
        TextView titleTextView  = (TextView) view.findViewById(R.id.title);

        String sectionColumnIndex  = cursor.getString(cursor.getColumnIndexOrThrow(NewsEntry.COLUMN_NEWS_SECTION));
        String dateColumnIndex  = cursor.getString(cursor.getColumnIndexOrThrow(NewsEntry.COLUMN_NEWS_DATE));
        String titleColumnIndex  = cursor.getString(cursor.getColumnIndexOrThrow(NewsEntry.COLUMN_NEWS_TITLE));

        sectionTextView.setText(null);
        sectionTextView.append(sectionColumnIndex);
        dateTextView.setText(null);
        dateTextView.append(dateColumnIndex);
        titleTextView.setText(null);
        titleTextView.append(titleColumnIndex);
    }
}