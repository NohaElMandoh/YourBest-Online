package com.example.prof_mohamedatef.yourbestonlinetv;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed Atef on 10/30/2016.
 */
public class OptionsEntity implements Serializable {

    OptionsEntity(){

    }
    String ImagePoster,OptionName,HotelRoomPrice,WebsiteURL,HotelUniqueId,Hotel_Name;

    public OptionsEntity(String IMAGEPOSTER, String websiteURL,String CourseName) {
        WebsiteURL = websiteURL;
        ImagePoster=IMAGEPOSTER;
        Hotel_Name=CourseName;
    }

    public String getHotelUniqueId() {
        return HotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        HotelUniqueId = hotelUniqueId;
    }


    public String getHotel_Name() {
        return Hotel_Name;
    }

    public void setHotel_Name(String hotel_Name) {
        Hotel_Name = hotel_Name;
    }

    public String getHotelRoomPrice() {
        return HotelRoomPrice;
    }

    public void setHotelRoomPrice(String hotelRoomPrice) {
        HotelRoomPrice = hotelRoomPrice;
    }

    public OptionsEntity(String imagePoster, String optionName) {

        ImagePoster = imagePoster;
        OptionName = optionName;
    }


    public String getWebsiteURL() {
        return WebsiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        WebsiteURL = websiteURL;
    }

    public OptionsEntity(String image_url_string, String title_string, String room_price_string, String webSiteURL,String ID_string) {
        ImagePoster=image_url_string;
        Hotel_Name=title_string;
        HotelRoomPrice=room_price_string;
        WebsiteURL=webSiteURL;
        HotelUniqueId=ID_string;
    }

    public OptionsEntity(String imagePoster, String optionName, String hotelRoomPrice, String websiteURL, String hotelUniqueId, String hotel_Name) {
        ImagePoster = imagePoster;
        OptionName = optionName;
        HotelRoomPrice = hotelRoomPrice;
        WebsiteURL = websiteURL;
        HotelUniqueId = hotelUniqueId;
        Hotel_Name = hotel_Name;

    }

    public String getImagePoster() {
        return ImagePoster;
    }

    public void setImagePoster(String imagePoster) {
        ImagePoster = imagePoster;
    }

    public String getOptionName() {
        return OptionName;
    }
    public void setOptionName(String optionName) {
        OptionName = optionName;
    }


}
