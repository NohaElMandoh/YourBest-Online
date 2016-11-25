package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Prof-Mohamed Atef on 11/4/2016.
 */
public class HotelOrdersCursorAdapter extends CursorAdapter {
    public HotelOrdersCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_hotels_orders, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView HotelName = (TextView) view.findViewById(R.id.Ordered_Hotel_title);
        TextView OrderNum = (TextView) view.findViewById(R.id.order_num);
        ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image

        // Extract properties from cursor
        String Hotel = cursor.getString(cursor.getColumnIndexOrThrow("Hotel_Name"));
        String ImagePoster = cursor.getString(cursor.getColumnIndexOrThrow("Hotel_ImagePath"));
        // Populate fields with extracted properties
        HotelName.setText(Hotel);
//        OrderNum.setText(String.valueOf());
        Picasso.with(context).load(ImagePoster).into(thumb_image);
    }
}
