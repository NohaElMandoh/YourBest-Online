package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Prof-Mohamed Atef on 12/4/2016.
 */
public class KFCListAdapter extends ArrayAdapter implements Serializable,Filterable {

//    KFCsFilter kfCsFilter;
    private ArrayList<OptionsEntity> mydata=new ArrayList<OptionsEntity>();
    private ArrayList<OptionsEntity> FilteredList;
    private ArrayList<OptionsEntity> feedOptionsList ;
    public transient Context mContext;

    public KFCListAdapter(Context context, int resource, ArrayList<OptionsEntity>feedOptionsList) {
        super(context, resource, feedOptionsList);
//        this.FilteredList=feedOptionsList;
        this.feedOptionsList=feedOptionsList;
        this.mContext=context;
    }
//
//    @Override
//    public Filter getFilter() {
//        if (kfCsFilter ==null){
//            kfCsFilter=new KFCsFilter();
//        }
//        return kfCsFilter;
//    }
//
//    private class KFCsFilter extends Filter {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults filterResults = new FilterResults();
//            if (constraint != null && constraint.length() > 0) {
//                constraint = constraint.toString().toUpperCase();
//
//                Holder h=new Holder(feedOptionsList);
//                for (int i = 0; i < h.Keepcounter.size(); i++) {
//                    FilteredList=h.Keepcounter;
//                    feedOptionsList=h.Keepcounter;
//                    if (FilteredList.get(i).getCity().toUpperCase().contains(constraint)) {
//                        OptionsEntity optionsEntity = feedOptionsList.get(i);
//                        if (optionsEntity.getCity()!=null&&optionsEntity.getAddress()!=null){
//                                mydata.add(optionsEntity);
//                        }else {
//                            Toast.makeText(getContext(), "Doesn't exist", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    feedOptionsList=h.Keepcounter;
//                }
//                filterResults.count = mydata.size();
//                filterResults.values = mydata;
//            } else {
//                filterResults.count = feedOptionsList.size();
//                filterResults.values = feedOptionsList;
//            }
//            return filterResults;
//        }
//
//        @SuppressWarnings("unchecked")
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            if (!results.values.equals(0)) {
//                feedOptionsList = (ArrayList<OptionsEntity>) results.values;
//                notifyDataSetChanged();
//            } else {
//                Toast.makeText(getContext(), "Doesn't exist", Toast.LENGTH_LONG).show();
//                feedOptionsList=FilteredList;
//            }
//        }
//    }
//
//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//    }

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
            view= LayoutInflater.from(mContext).inflate(R.layout.kfc_listitem,parent,false);
        }
        TextView City = (TextView)view.findViewById(R.id.City); // title
        City.setText(feedItem.getCity());
        TextView Address = (TextView)view.findViewById(R.id.Address); // title
        Address.setText(feedItem.getAddress());
        ImageView thumb_image=(ImageView)view.findViewById(R.id.kfc_image); // thumb image
        Picasso.with(mContext).load(feedItem.getImagePoster()).into(thumb_image);
        return view;
    }
}