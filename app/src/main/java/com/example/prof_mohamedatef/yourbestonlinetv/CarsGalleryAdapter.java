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

/**
 * Created by Prof-Mohamed Atef on 11/4/2016.
 */
public class CarsGalleryAdapter extends ArrayAdapter {
    private ArrayList<CarsEntity> feedOptionsList;
    public transient Context mContext;

    public CarsGalleryAdapter (Context context, int Resource,ArrayList<CarsEntity>feedOptionsList) {
        super(context, Resource, feedOptionsList);
        this.feedOptionsList=feedOptionsList;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return feedOptionsList.size();
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
        final CarsEntity feedItem=feedOptionsList.get(i);
        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.list_row,parent,false);
        }
        TextView title = (TextView)view.findViewById(R.id.title); // title
        title.setText(feedItem.getCarType());
        ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image
        Picasso.with(mContext).load(feedItem.getImagePath()).into(thumb_image);
        return view;
    }
}
