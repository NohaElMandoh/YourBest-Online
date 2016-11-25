package com.example.prof_mohamedatef.yourbestonlinetv;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed Atef on 11/3/2016.
 */
public class HotelEntity implements Serializable {

String ByUserName, RoomNum, RoomPrice, DateFrom, DateTo, Total, Hotel_Name, Hotel_ImagePath, Hotel_website,HotelID,HotelRoomsOrderID;



    HotelEntity(){

    }
    public HotelEntity(String HotelID,String byUserName, String roomNum,String RoomPrice, String dateFrom, String dateTo, String total, String hotel_Name, String hotel_ImagePath, String hotel_website) {
        this.HotelID=HotelID;
        ByUserName = byUserName;
        RoomNum = roomNum;
        DateFrom = dateFrom;
        DateTo = dateTo;
        Total = total;
        Hotel_Name = hotel_Name;
        Hotel_ImagePath = hotel_ImagePath;
        Hotel_website = hotel_website;
        this.RoomPrice=RoomPrice;
    }
    public String getRoomPrice() {
        return RoomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        RoomPrice = roomPrice;
    }
    public String getHotelRoomsOrderID() {
        return HotelRoomsOrderID;
    }

    public void setHotelRoomsOrderID(String hotelRoomsOrderID) {
        HotelRoomsOrderID = hotelRoomsOrderID;
    }

    public String getHotelID() {
        return HotelID;
    }

    public void setHotelID(String hotelID) {
        HotelID = hotelID;
    }

    public String getByUserName() {
        return ByUserName;
    }

    public void setByUserName(String byUserName) {
        ByUserName = byUserName;
    }

    public String getRoomNum() {
        return RoomNum;
    }

    public void setRoomNum(String roomNum) {
        RoomNum = roomNum;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getHotel_Name() {
        return Hotel_Name;
    }

    public void setHotel_Name(String hotel_Name) {
        Hotel_Name = hotel_Name;
    }

    public String getHotel_ImagePath() {
        return Hotel_ImagePath;
    }

    public void setHotel_ImagePath(String hotel_ImagePath) {
        Hotel_ImagePath = hotel_ImagePath;
    }

    public String getHotel_website() {
        return Hotel_website;
    }

    public void setHotel_website(String hotel_website) {
        Hotel_website = hotel_website;
    }
}
