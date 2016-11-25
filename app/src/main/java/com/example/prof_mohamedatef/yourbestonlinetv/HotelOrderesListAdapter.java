package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prof-Mohamed Atef on 11/3/2016.
 */
public class HotelOrderesListAdapter extends ArrayAdapter {

    private ArrayList<HotelEntity> feedHotelList;
    public transient Context mContext;

    public HotelOrderesListAdapter(Context context, int Resource,ArrayList<HotelEntity>feedHotelList) {
        super(context, Resource, feedHotelList);
        this.feedHotelList=feedHotelList;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return feedHotelList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        final HotelEntity feedItem=feedHotelList.get(i);
        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.list_hotels_orders,parent,false);
        }
        TextView title = (TextView)view.findViewById(R.id.Ordered_Hotel_title); // title
        title.setText(feedItem.getHotel_Name());
        TextView ordernum = (TextView)view.findViewById(R.id.order_num); // title
        ordernum.setText(feedItem.getHotelRoomsOrderID());
        ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image
        Picasso.with(mContext).load(feedItem.getHotel_ImagePath()).into(thumb_image);
        return view;
    }
}