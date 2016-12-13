package com.example.prof_mohamedatef.yourbestonlinetv;

import java.util.ArrayList;

/**
 * Created by Prof-Mohamed Atef on 11/25/2016.
 */
public class Holder {
    //            optionsEntity = (OptionsEntity) intent.getSerializableExtra("HotelInfo");
//            String HotelNAme=optionsEntity.getHotel_Name().toString();
//            String ImagePath=optionsEntity.getImagePoster().toString();
//            String URL= optionsEntity.getWebsiteURL().toString();
//            String HotelID= optionsEntity.getHotelUniqueId().toString();
//            String HotelRoom=optionsEntity.getHotelRoomPrice();

    public static String HotelName, ImagePath, URL, HotelID, HotelRoom;
    public ArrayList<OptionsEntity> counter;
    public ArrayList<OptionsEntity> Keepcounter;

    public Holder( ArrayList<OptionsEntity> keepcounter) {
        Keepcounter = keepcounter;
    }

    public Holder() {
    }
}