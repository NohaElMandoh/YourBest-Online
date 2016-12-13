package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Prof-Mohamed Atef on 10/29/2016.
 */
public class LazyAdapter extends ArrayAdapter implements Serializable{

    private ArrayList<OptionsEntity> feedOptionsList;
    public transient Context mContext;

    public LazyAdapter(Context context, int Resource,ArrayList<OptionsEntity>feedOptionsList) {
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
        final OptionsEntity feedItem=feedOptionsList.get(i);
        View view=convertView;
        if(view==null){
            view=LayoutInflater.from(mContext).inflate(R.layout.list_row,parent,false);
        }
        TextView Title = (TextView)view.findViewById(R.id.title); // title
        Title.setText(feedItem.getOptionName());

        ImageView thumb_image=(ImageView)view.findViewById(R.id.list_image); // thumb image
        Picasso.with(mContext).load(feedItem.getImagePoster()).into(thumb_image);
        return view;
    }
}


