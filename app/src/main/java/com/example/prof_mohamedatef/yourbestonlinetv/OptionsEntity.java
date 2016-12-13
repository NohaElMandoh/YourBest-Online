package com.example.prof_mohamedatef.yourbestonlinetv;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed Atef on 10/30/2016.
 */
public class OptionsEntity implements Serializable {

    OptionsEntity(){

    }
    String ImagePoster;
    String OptionName;
    String HotelRoomPrice;
    String WebsiteURL;
    String HotelUniqueId;
    String Hotel_Name;
    String POSTAL_CODE;
    String KFC_ID;
    String Country;
    String Address;
    String City;
    String Website_Url;

    public String getWebsite_Url() {
        return Website_Url;
    }

    public void setWebsite_Url(String website_Url) {
        Website_Url = website_Url;
    }

    String Latitude;
    String Langitude;

    public String getKFC_ID() {
        return KFC_ID;
    }

    public void setKFC_ID(String KFC_ID) {
        this.KFC_ID = KFC_ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLangitude() {
        return Langitude;
    }

    public void setLangitude(String langitude) {
        Langitude = langitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public OptionsEntity(String id_string, String image_url_string, String country_string, String address_string, String city_string, String webSite_url_string, String postal_code_string, String latitude_string, String langitude_string) {
        KFC_ID=id_string;
        ImagePoster=image_url_string;
        Country=country_string;
        Address=address_string;
        City=city_string;
        Website_Url=webSite_url_string;
        POSTAL_CODE=postal_code_string;
        Latitude=latitude_string;
        Langitude=langitude_string;
    }


    public String getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    public void setPOSTAL_CODE(String POSTAL_CODE) {
        this.POSTAL_CODE = POSTAL_CODE;
    }

    public OptionsEntity(String IMAGEPOSTER, String websiteURL, String CourseName) {
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
