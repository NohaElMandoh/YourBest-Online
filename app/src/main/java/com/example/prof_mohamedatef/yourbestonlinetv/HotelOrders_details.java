package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HotelOrders_details extends Activity {

    HotelEntity hotelEntity;
    String HotelName,Username,OrderID,RoomsNums, DateFrom, Dateto, TotalPrice,RoomPricePerNight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_orders_details);

        this.setTitle("YourBest Hotel Orders Details");
        TextView txt_username_hotelorders,txt_ordernum_hotelorders,txt_hotelname_hotelorders,txt_roomnums_hotelorders,
                txt_Datefrom_hotelorders,txt_Dateto_hotelorders,txt_roomprice_hotelorders,txt_totalcashrate_hotelorders;

        txt_username_hotelorders=(TextView)findViewById(R.id.txt_username_hotelorders);
        txt_ordernum_hotelorders=(TextView)findViewById(R.id.txt_ordernum_hotelorders);
        txt_hotelname_hotelorders=(TextView)findViewById(R.id.txt_hotelname_hotelorders);
        txt_roomnums_hotelorders=(TextView)findViewById(R.id.txt_roomnums_hotelorders);
        txt_Datefrom_hotelorders=(TextView)findViewById(R.id.txt_Datefrom_hotelorders);
        txt_Dateto_hotelorders=(TextView)findViewById(R.id.txt_Dateto_hotelorders);
        txt_roomprice_hotelorders=(TextView)findViewById(R.id.txt_roomprice_hotelorders);
        txt_totalcashrate_hotelorders=(TextView)findViewById(R.id.txt_totalcashrate_hotelorders);

        final Intent intent = getIntent();
        if (intent != null) {
            hotelEntity = (HotelEntity) intent.getSerializableExtra("HotelOrderInfo");
            HotelName= hotelEntity.getHotel_Name();
            Username=hotelEntity.getByUserName();
            OrderID=hotelEntity.getHotelRoomsOrderID();
            RoomsNums=hotelEntity.getRoomNum();
            DateFrom=hotelEntity.getDateFrom();
            Dateto=hotelEntity.getDateTo();
            TotalPrice=hotelEntity.getTotal();
            RoomPricePerNight=hotelEntity.getRoomPrice();
            txt_username_hotelorders.setText(Username);
            txt_ordernum_hotelorders.setText(OrderID);
            txt_hotelname_hotelorders.setText(HotelName);
            txt_roomnums_hotelorders.setText(RoomsNums);
            txt_Datefrom_hotelorders.setText(DateFrom);
            txt_Dateto_hotelorders.setText(Dateto);
            txt_roomprice_hotelorders.setText(RoomPricePerNight);
            txt_totalcashrate_hotelorders.setText(TotalPrice);
        }
    }
}