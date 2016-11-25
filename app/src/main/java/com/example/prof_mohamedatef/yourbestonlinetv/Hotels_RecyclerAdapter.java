package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prof-Mohamed on 8/3/2016.
 */

public class Hotels_RecyclerAdapter extends RecyclerView.Adapter<Hotels_RecyclerAdapter.ViewHOlder> implements Serializable {

    private List<OptionsEntity> feedItemList;

    public transient Context mContext;

    public Hotels_RecyclerAdapter(Context context, ArrayList<OptionsEntity> feedItemList) {
        this.feedItemList=feedItemList;
        this.mContext= context;
    }

    @Override
    public ViewHOlder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotels_row, null);
        RecyclerView.ViewHolder viewHolder=new ViewHOlder(view);
        return (ViewHOlder) viewHolder;
    }
    OptionsEntity feedItem;

    @Override
    public void onBindViewHolder(ViewHOlder customViewholder, final int i) {
        feedItem=feedItemList.get(i);
        Picasso.with(mContext).load(feedItem.getImagePoster()).into(customViewholder.one_img);
        customViewholder.one_text.setText(feedItem.getHotel_Name());
        customViewholder.one_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Booking_hotels.class)
                        .putExtra("HotelInfo",feedItem=feedItemList.get(i));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });
        }



    @Override
    public int getItemCount() {
        return (null!=feedItemList?feedItemList.size():0);
    }



    public class ViewHOlder extends RecyclerView.ViewHolder {
        protected ImageView one_img;
        protected TextView one_text;
        public ViewHOlder(View converview) {
            super(converview);
            this.one_img = (ImageView) converview.findViewById(R.id.img_view);
            this.one_text = (TextView) converview.findViewById(R.id.txt_poster_title);
        }
    }
}