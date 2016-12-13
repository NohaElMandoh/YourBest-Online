package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class hotel_orders extends AppCompatActivity {

    private final String LOG_TAG = androidlistviewactivity.class.getSimpleName();

    HashMap<String, String> user;
    ArrayList<UsersEntity> Logger;
    SessionManagement sessionManagement;
    DBHelper myDB;
    ListView listView;
    HotelOrderesListAdapter hotelOrderesListAdapter;
    String LoggedUser=null;
    ArrayList<HotelEntity> hotelEntity;
    @Override
    protected void onStart() {
        super.onStart();
        HashMap<String, String> user =sessionManagement.getUserDetails();
//        String name = user.get(SessionManagement.KEY_NAME);
        String email = user.get(SessionManagement.KEY_EMAIL);
        LoggedUser=email;
//        PopulateHotelOrdersListView(email);
//        PopulateHotelOrdersListView();
        invalidateOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        String email = user.get(SessionManagement.KEY_EMAIL);
//        PopulateHotelOrdersListView(email);
    }
    String email;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        user =sessionManagement.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        email = user.get(SessionManagement.KEY_EMAIL);
        if (email==null){
            menu.findItem(R.id.Username_orders).setTitle("Sign In");
            LoggedUser=null;
            menu.findItem(R.id.logout).setTitle("");
        }else {
            menu.findItem(R.id.Username_orders).setTitle(email);
            LoggedUser=email;
            menu.findItem(R.id.logout).setTitle("Log Out");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_orders);
        this.setTitle("YourBest Hotels Orders");
        listView=(ListView)findViewById(R.id.list_hotelOrders);
        try {
            ViewConfiguration config = ViewConfiguration.get(getBaseContext());
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }
        sessionManagement= new SessionManagement(getApplicationContext());

        try{
            myDB = new DBHelper(this);
        }catch (Exception e){
            Log.e(LOG_TAG,"Didn't Create Database",e);
        }

        user =sessionManagement.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String email = user.get(SessionManagement.KEY_EMAIL);
//            hotelOrderesListAdapter = new HotelOrderesListAdapter(getApplicationContext(), R.layout.list_hotels_orders, new ArrayList<HotelEntity>());
//            listView.setAdapter(hotelOrderesListAdapter);
        PopulateHotelOrdersListView(email);
    }

    public void DialogueYou_havenot_reserved_Orders(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sorry, You haven't reserved Orders yet")
                .setTitle("Orders Reservation")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

    public void DialogueDeletedSuccessfully(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Successful")
                .setTitle("Nice")
                .setNegativeButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }
    public void PopulateHotelOrdersListView(String email){
        hotelEntity= myDB.getHotelsOrderedDataByUserName(email);
//        if (hotelEntity.size()==0){
//            DialogueYou_havenot_reserved_Orders();
//        }else {
            hotelOrderesListAdapter = new HotelOrderesListAdapter(getApplicationContext(), R.layout.list_hotels_orders, hotelEntity);
            hotelOrderesListAdapter.notifyDataSetChanged();
            listView.setAdapter(hotelOrderesListAdapter);
            hotelOrderesListAdapter.notifyDataSetChanged();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    switch (position){
//                        case 0:
                            Intent intent = new Intent(getBaseContext(),HotelOrders_details.class)
                                    .putExtra("HotelOrderInfo",hotelEntity.get(position));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent);
//                            Intent intent_Hotels = new Intent(getApplicationContext(), Hotels.class);
//                            intent_Hotels.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            getApplicationContext().startActivity(intent_Hotels);
//                            break;
//                        case 1:
//                            Intent intent_Burger_King = new Intent();
//                            intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                            getApplicationContext().startActivity(intent_Burger_King);
//                            break;
//                    }
                }
            });

//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_orders, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         switch (item.getItemId()){
             case R.id.Username_orders:
                 if (item.getTitle().toString().equals("Sign In")){
                     Intent intent_SignIN = new Intent(this, MainActivity.class);
                     intent_SignIN.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     getApplicationContext().startActivity(intent_SignIN);
                 }else {
                     String itemTitle=item.getTitle().toString();
                     Logger=myDB.getLoggedUserInfo(itemTitle);
                     Intent intent_LoggerDetails = new Intent(this, Profile.class);
                     Bundle b = new Bundle();
                     b.putSerializable("LoggerInfo", Logger);
                     intent_LoggerDetails.putExtras(b);
                     startActivity(intent_LoggerDetails);
                 }
                 break;
             case R.id.clear_orders:
                 boolean deleted=myDB.clearOrdersInTBLHotelReservations(email);
                 if (deleted==true){
                     DialogueDeletedSuccessfully();
                     PopulateHotelOrdersListView(email);
                 }
                 break;
             case R.id.logout:
                 sessionManagement.logoutUser();
                 break;
         }
        return super.onOptionsItemSelected(item);
    }
}