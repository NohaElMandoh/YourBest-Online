package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.TotalCaptureResult;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Booking_hotels extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private final String LOG_TAG = Booking_hotels.class.getSimpleName();

    public int roomPrice=0;
    SessionManagement sessionManagement;
    String email;//loggeduser
    DBHelper myDB;
    static int num;
    public int ref;
    private android.os.Handler handler;
    ArrayList<UsersEntity> Logger;
    HashMap<String, String> user;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // parent.getItemAtPosition(position);
        num=position;
//        if (position==0) {
//            position++;
//        }//else if (position==1){
//            position+=2;
//        }else if (position==2){
//            position+=3;
//        }else if
//            num =position;

//                String item=parent.getAdapter().getItem(position-1).toString();
//        Toast.makeText(parent.getContext(), "Selected Rooms : " + item, Toast.LENGTH_LONG).show();
        ref=num;
        txt_total_payment.setText(String.valueOf(getTotal()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    Spinner spinner;
    TextView txt_hotel_name, Price, txt_total_payment;
    ImageView Hotel_Image;
    EditText txt_FromDate,
            txt_ToDate;
    Button btn_Confirm_reservation;


    final Calendar c = Calendar.getInstance();
    final int year = c.get(Calendar.YEAR);
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);

    OptionsEntity optionsEntity;
    String postal_CODE, url, StartFrom, EndAt, TotalPayment;
    boolean verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_hotels);
        this.setTitle("YourBest Hotels Reservation");
        sessionManagement= new SessionManagement(getApplicationContext());
        try{
            myDB = new DBHelper(this);
        }catch (Exception e){
            Log.e(LOG_TAG,"Didn't Create Database",e);
        }


        int InitialIndex= myDB.SelectZeroHotelOrdersUniqueIDStartingValue();
        if (InitialIndex==0){
            initialValueforHotelOrderUniqueID();
        }

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
        txt_FromDate = (EditText) findViewById(R.id.txt_from_data_hotels_booking);
        txt_ToDate = (EditText) findViewById(R.id.txt_to_date_hotels_booking);
        txt_hotel_name = (TextView) findViewById(R.id.txt_hotel_name);
        Price = (TextView) findViewById(R.id.txt_pricePerRoom);
        txt_total_payment = (TextView) findViewById(R.id.txt_total_payment);
        Hotel_Image = (ImageView) findViewById(R.id.Hotel_Image);
        btn_Confirm_reservation = (Button) findViewById(R.id.btn_Confirm_reservation);

        txt_total_payment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Toast.makeText(getApplicationContext(), "Please, Verify Date To After Date From" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                TotalPayment= txt_total_payment.getText().toString();
            }
        });
//        if (savedInstanceState!=null&&savedInstanceState.getString("HotelNAme")!=null){
//            txt_hotel_name.setText(savedInstanceState.getString("HotelNAme"));
//            Picasso.with(getApplicationContext()).load(savedInstanceState.getString("ImagePath")).into(Hotel_Image);
//            Price.setText(savedInstanceState.getString("HotelRoom"));//+ " USD"
//            url= savedInstanceState.getString("URL");
//        }else {
            final Intent intent = getIntent();
            if (intent != null) {
                try{
                    optionsEntity = (OptionsEntity) intent.getSerializableExtra("HotelInfo");
                    txt_hotel_name.setText(optionsEntity.getHotel_Name());
                    Picasso.with(getApplicationContext()).load(optionsEntity.getImagePoster()).into(Hotel_Image);
                    Price.setText(optionsEntity.getHotelRoomPrice());//+ " USD"
                    url= optionsEntity.getWebsiteURL();
                    postal_CODE=optionsEntity.getPOSTAL_CODE();
                }catch(Exception e){
                    txt_hotel_name.setText(h.HotelName);
                    Picasso.with(getApplicationContext()).load(h.ImagePath).into(Hotel_Image);
                    Price.setText(h.HotelRoom);//+ " USD"
                    url= h.URL;
                }
            }
//        }

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        //Spinner Drop Down elements
        ArrayList<String> rooms_num = new ArrayList<String>();
        rooms_num.add("1");
        rooms_num.add("2");
        rooms_num.add("3");
        rooms_num.add("4");
        rooms_num.add("5");
        rooms_num.add("6");

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getApplicationContext(),rooms_num);
        spinner.setAdapter(customSpinnerAdapter);

        txt_FromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog DatePick = new DatePickerDialog(Booking_hotels.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), "Please, Verify Date To After Date From" , Toast.LENGTH_LONG).show();
                        int month_int = ProduceMonth(month);
                        txt_FromDate.setText(dayOfMonth + "/" + month_int + "/" + year);
                        txt_total_payment.setText(String.valueOf(getTotalAtFromTODate(ref)));
                        StartFrom= txt_FromDate.getText().toString();
                    }
                }, year, month, day);
                DatePick.setTitle("Start Date");
                DatePick.show();
            }
        });

        txt_ToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog DatePick = new DatePickerDialog(Booking_hotels.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(), "Please, Verify Date To After Date From" , Toast.LENGTH_LONG).show();
                        int month_int = ProduceMonth(month);
                        txt_ToDate.setText(dayOfMonth + "/" + month_int + "/" + year);
                        txt_total_payment.setText(String.valueOf(getTotalAtFromTODate(ref)));
                        EndAt=txt_ToDate.getText().toString();
//                        Date d1 = null, d2 = null;
//                            try {
//
//                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//                                d1 = format.parse(String.valueOf(txt_FromDate.getText()));
//                                d2 = format.parse(String.valueOf(txt_ToDate.getText()));
//                                verify = getDateToAfterDateFrom(d1, d2);
//                            } catch (Exception e) {
//                                verify = getDateToAfterDateFrom(d1, d2);

//                            }

                    }
                }, year, month, day);
                DatePick.setTitle("End Date");
                DatePick.show();
            }
        });
    }
    Holder h = new Holder();
    @Override
    protected void onPause() {
        super.onPause();
        final Intent intent=getIntent();
        if (intent!=null) {
            try{

                optionsEntity = (OptionsEntity) intent.getSerializableExtra("HotelInfo");
//            String HotelNAme=optionsEntity.getHotel_Name().toString();
//            String ImagePath=optionsEntity.getImagePoster().toString();
//            String URL= optionsEntity.getWebsiteURL().toString();
//            String HotelID= optionsEntity.getHotelUniqueId().toString();
//            String HotelRoom=optionsEntity.getHotelRoomPrice();

                h.HotelName = optionsEntity.getHotel_Name().toString();
                h.ImagePath = optionsEntity.getImagePoster().toString();
                h.URL = optionsEntity.getWebsiteURL().toString();
                h.HotelID = optionsEntity.getHotelUniqueId().toString();
                h.HotelRoom = optionsEntity.getHotelRoomPrice().toString();
            }catch (Exception e){

            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        HashMap<String, String> user =sessionManagement.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        email = user.get(SessionManagement.KEY_EMAIL);
        if (email==null){
            menu.findItem(R.id.User).setTitle("Sign In");
            LoggedUser=null;
        }else {
            menu.findItem(R.id.User).setTitle(email);
            LoggedUser=(String) menu.findItem(R.id.User).getTitle().toString();
        }

//        String MenuItem=(String) menu.findItem(R.id.Orders).getTitle().toString();
//    if(MenuItem=="Orders"){
//        Intent intent_hotel_orders = new Intent(getApplicationContext(), hotel_orders.class);
//        intent_hotel_orders.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getApplicationContext().startActivity(intent_hotel_orders);
//    }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        invalidateOptionsMenu();
    }

    String LoggedUser="";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hotels_menu, menu);//Menu Resource, Menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.website:
                switch (url){
                    case "http://www.ritzcarlton.com/en/hotels/middle-east/cairo":
                        Intent intentNileRitzCarlton = new Intent();
                        intentNileRitzCarlton.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentNileRitzCarlton.setData(Uri.parse("http://www.ritzcarlton.com/en/hotels/middle-east/cairo"));
                        getApplicationContext().startActivity(intentNileRitzCarlton);
                        break;
                    case "http://www3.hilton.com/en/hotels/egypt/ramses-hilton-CAIRHTW/index.html":
                        Intent intentRamsesHilton = new Intent();
                        intentRamsesHilton.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intentRamsesHilton.setData(Uri.parse("http://www3.hilton.com/en/hotels/egypt/ramses-hilton-CAIRHTW/index.html"));
                        getApplicationContext().startActivity(intentRamsesHilton);
                        break;
                    default:
                        break;
                }
                break;
            case R.id.location:
                openPreferredLocationInMap();
                break;
            case R.id.Orders:
                user =sessionManagement.getUserDetails();
                email = user.get(SessionManagement.KEY_EMAIL);
                int orders= myDB.getHotelOrderingTBL_MAX_OrderSid(email);
                boolean isLogged= sessionManagement.isLoggedIn();
                    if (isLogged==true){
                        if ( orders!=0){
                            Intent intent_hotel_orders = new Intent(getApplicationContext(), hotel_orders.class);
                            intent_hotel_orders.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_hotel_orders);
                        }else {
                            DialogueOrdersUnavailable();
                        }
                    } else {
                        DialogueOrdersUnavailableDueRegisteration();
                    }
                break;
            case R.id.User:
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
            default:
                break;
        }
//        if (item.getTitle().toString().matches("Our Website")) {
//            switch (url){
//                case "http://www.ritzcarlton.com/en/hotels/middle-east/cairo":
//                    Intent intentNileRitzCarlton = new Intent();
//                    intentNileRitzCarlton.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intentNileRitzCarlton.setData(Uri.parse("http://www.ritzcarlton.com/en/hotels/middle-east/cairo"));
//                    getApplicationContext().startActivity(intentNileRitzCarlton);
//                    break;
//                case "http://www3.hilton.com/en/hotels/egypt/ramses-hilton-CAIRHTW/index.html":
//                    Intent intentRamsesHilton = new Intent();
//                    intentRamsesHilton.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intentRamsesHilton.setData(Uri.parse("http://www3.hilton.com/en/hotels/egypt/ramses-hilton-CAIRHTW/index.html"));
//                    getApplicationContext().startActivity(intentRamsesHilton);
//                    break;
//                default:
//                    break;
//            }
//        }else if (item.getTitle().toString().matches("location")) {
//        }else if (!item.getTitle().toString().matches("")){
//            String itemTitle=item.getTitle().toString();
//            Logger=myDB.getLoggedUserInfo(itemTitle);
//            Intent intent_LoggerDetails = new Intent(this, Profile.class);
//            Bundle b = new Bundle();
//            b.putSerializable("LoggerInfo", Logger);
//            intent_LoggerDetails.putExtras(b);
//            startActivity(intent_LoggerDetails);
//        }else if(item.getTitle().toString().matches("Orders")){
//            if (LoggedUser.matches("")){
//                DialogueOrdersUnavailable();
//            }else {
//                Intent intent_hotel_orders = new Intent(getApplicationContext(), hotel_orders.class);
//                intent_hotel_orders.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getApplicationContext().startActivity(intent_hotel_orders);
//            }
//        }else if ((item.getTitle().toString().matches("Sign In"))){
//            Intent intent_MainActivity = new Intent(getApplicationContext(), MainActivity.class);
//            intent_MainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            getApplicationContext().startActivity(intent_MainActivity);
//        }
//        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
//                    SharedPreferences sharedPrefs =
//                                    PreferenceManager.getDefaultSharedPreferences(this);
//                    String location = sharedPrefs.getString(
////                                    getString(R.string.pref_location_key),
////                                    getString(R.string.pref_location_default));
        //1113 Corniche El Nile, Nile Corniche, Cairo, Cairo Governorate
        // 1115 كورنيش النيل، شركس، Cairo, محافظة القاهرة‬ 12344
        String location = null;
//        switch (url) {
//            case "http://www.ritzcarlton.com/en/hotels/middle-east/cairo":
//                location = "1113 Corniche El Nile, Nile Corniche, Cairo, Cairo Governorate";
////                        Intent intentNileRitzCarlton = new Intent();
////                        intentNileRitzCarlton.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        intentNileRitzCarlton.setData(Uri.parse("http://www.ritzcarlton.com/en/hotels/middle-east/cairo"));
////                        getApplicationContext().startActivity(intentNileRitzCarlton);
//                break;
//            case "http://www3.hilton.com/en/hotels/egypt/ramses-hilton-CAIRHTW/index.html":
//                location = "1115 كورنيش النيل، شركس، Cairo, محافظة القاهرة\u202C 12344";
////                        Intent intentRamsesHilton = new Intent();
////                        intentRamsesHilton.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        intentRamsesHilton.setData(Uri.parse("http://www3.hilton.com/en/hotels/egypt/ramses-hilton-CAIRHTW/index.html"));
////                        getApplicationContext().startActivity(intentRamsesHilton);
//                break;
//            default:
//                break;
//        }
                            // Using the URI scheme for showing a location found on a map.  This super-handy
                                    // intent can is detailed in the "Common Intents" page of Android's developer site:
                                            // http://developer.android.com/guide/components/intents-common.html#Maps
        location=postal_CODE;
                                                    Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                                    .appendQueryParameter("q", location)
                                    .build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(geoLocation);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        } else {
                            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
                        }
    }

    public void DialogueOrdersUnavailableDueRegisteration(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("please sign in or up then reserve your room to view your latest orders.\n\nyourbest-online.com\nThanks")
                .setTitle("Login First")
                .setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent_Registeration = new Intent(getApplicationContext(), Registeration.class);
                        intent_Registeration.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent_Registeration);
                    }
                });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Sign In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent_Login = new Intent(getApplicationContext(), Login.class);
                        intent_Login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent_Login);
                    }
                });
                // Create the AlertDialog object and return it
                AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    public void DialogueOrdersUnavailable(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You don't have any orders.\n\nyourbest-online.com\nThanks")
                .setTitle("Empty")
                .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }

                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }
    public boolean getDateToAfterDateFromOrEqual(Date d1,Date d2){
        boolean result = false;
         String FromDate= txt_FromDate.getText().toString();
        String ToDate= txt_ToDate.getText().toString();
        if (!FromDate.matches("")&&! ToDate.matches("")){
            if (d2.after(d1)||d2.equals(d1)) {
                result= true;
            }else {
                result = false;
            }
        }
        return result;
    }
    //
    public double getTotal() {
        double Total = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = null, d2 = null;

            d1 = format.parse(String.valueOf(txt_FromDate.getText()));
            d2 = format.parse(String.valueOf(txt_ToDate.getText()));
            int DiffDate = 0;
            int p = Integer.parseInt((String) Price.getText());
            num++;
            double rooms_price_per_night = num * p;

            if (d2.after(d1)) {
                DiffDate = getDifferenceDays(d1, d2);
                Total = DiffDate * rooms_price_per_night;
            }else if (d2.equals(d1)) {
                DiffDate = getDifferenceDays(d1, d2);
                Total = DiffDate * rooms_price_per_night;
            }
            num=0;
            return Total;
        } catch (ParseException e) {
            e.printStackTrace();
            num=0;
            return 0;
        }
    }

    public int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }


//    public boolean getDayTOAfterDayFrom(Date d1, Date d2) {
//        int daysdiff = 0;
//        long diff = d2.getTime() - d1.getTime();
//        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
//        daysdiff = (int) diffDays;
//        return daysdiff;
//    }
    //******************
    public double getTotalAtFromTODate(int ref) {
        double Total = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = null, d2 = null;
            d1 = format.parse(String.valueOf(txt_FromDate.getText()));
            d2 = format.parse(String.valueOf(txt_ToDate.getText()));
            int DiffDate = 0;
            roomPrice = Integer.parseInt((String) Price.getText());

            if (num==0){
                ref++;
                double rooms_price_per_night = ref * roomPrice;

                if (d2.after(d1)) {
                    DiffDate = getDifferenceDays(d1, d2);
                    Total = DiffDate * rooms_price_per_night;
                }else if (d2.equals(d1)){
                    DiffDate = getDifferenceDays(d1, d2);
                    Total = DiffDate * rooms_price_per_night;
                }
            }
            num=0;
            return Total;
        } catch (ParseException e) {
            e.printStackTrace();
            num=0;
            return 0;
        }
    }

    public int ProduceMonth(int month) {
        int month_int = 0;
        switch (month) {
            case 0:
                month_int = 1;
                break;
            case 1:
                month_int = 2;
                break;
            case 2:
                month_int = 3;
                break;
            case 3:
                month_int = 4;
                break;
            case 4:
                month_int = 5;
                break;
            case 5:
                month_int = 6;
                break;
            case 6:
                month_int = 7;
                break;
            case 7:
                month_int = 8;
                break;
            case 8:
                month_int = 9;
                break;
            case 9:
                month_int = 10;
                break;
            case 10:
                month_int = 11;
                break;
            case 11:
                month_int = 12;
                break;
        }
        return month_int;
    }

    public void Confirm_Reservation(View view) {
        user =sessionManagement.getUserDetails();

        email = user.get(SessionManagement.KEY_EMAIL);
        boolean verifyNOTAfterEquaL=false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = null, d2 = null;
            d1 = format.parse(String.valueOf(txt_FromDate.getText()));
            d2 = format.parse(String.valueOf(txt_ToDate.getText()));
             verifyNOTAfterEquaL= getDateToAfterDateFromOrEqual(d1,d2);
        }catch (Exception e){
            e.printStackTrace();
        }
        String CashRate =txt_total_payment.getText().toString();
//        CustomSpinnerAdapter customSpinnerAdapter=null;
//        String Roomnums=customSpinnerAdapter.txt.getText().toString();
        String Roomnums= spinner.getSelectedItem().toString();
        if (CashRate!="0.0"){
            if (verifyNOTAfterEquaL==true){
                final Intent intent = getIntent();
                if (intent != null) {
                    boolean isLogged= sessionManagement.isLoggedIn();
                    if (isLogged==true &&!Roomnums.matches("")&&!StartFrom.matches("")&&!EndAt.matches("")&&!TotalPayment.matches("")){
                        optionsEntity = (OptionsEntity) intent.getSerializableExtra("HotelInfo");


                            try{

//                                if (optionsEntity!=null) {
                                    int maxrowID = myDB.SelectZeroHotelOrdersUniqueIDStartingValue();
                                    //                        int maxrowID= myDB.getHotelOrderingTBL_MAX_OrderSid(email);

                                    if (maxrowID != 0) {
//                            int initialID=myDB.SelectZeroHotelOrdersUniqueIDStartingValue();
//
//                            if (initialID==0){

                                        maxrowID++;
                                        boolean OrdersInserted = myDB.insertOrderNumsTable(String.valueOf(maxrowID));
                                        boolean ReservationInserted = myDB.insertConfirmTBLHotelReservations(
                                                optionsEntity.getHotelUniqueId(),
                                                email,
                                                Roomnums,
                                                StartFrom,
                                                EndAt,
                                                TotalPayment,
                                                optionsEntity.getHotel_Name(),
                                                optionsEntity.getImagePoster(),
                                                optionsEntity.getWebsiteURL(),
                                                String.valueOf(maxrowID),
                                                String.valueOf(roomPrice)
                                        );
                                        if (ReservationInserted == true && OrdersInserted == true) {
//                            Toast.makeText(getApplicationContext(), "Reservation Confirmed Successfully", Toast.LENGTH_LONG).show();
                                            DialogueSuccessful();
                                            txt_FromDate.setText("");
                                            txt_ToDate.setText("");
                                            txt_total_payment.setText("");
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Did not Insert Data", Toast.LENGTH_LONG).show();
                                        }
                                    }
//                        else{
//                                maxrowID+=maxrowID;
//                                boolean OrdersInserted=myDB.insertOrderNumsTable(String.valueOf(maxrowID));
//                                boolean ReservationInserted= myDB.insertConfirmTBLHotelReservations(
//                                        optionsEntity.getHotelUniqueId(),
//                                        email,
//                                        Roomnums,
//                                        StartFrom,
//                                        EndAt,
//                                        TotalPayment,
//                                        optionsEntity.getHotel_Name(),
//                                        optionsEntity.getImagePoster(),
//                                        optionsEntity.getWebsiteURL(),
//                                        String.valueOf(maxrowID),
//                                        String.valueOf(roomPrice)
//                                );
//                                if (ReservationInserted==true&&OrdersInserted==true){
////                            Toast.makeText(getApplicationContext(), "Reservation Confirmed Successfully", Toast.LENGTH_LONG).show();
//                                    DialogueSuccessful();
//                                    txt_FromDate.setText("");
//                                    txt_ToDate.setText("");
//                                    txt_total_payment.setText("");
//                                }else {
//                                    Toast.makeText(getApplicationContext(), "Did not Insert Data", Toast.LENGTH_LONG).show();
//                                }
////                            }

//                        }


                            }catch (Exception e){
                                int maxrowID = myDB.SelectZeroHotelOrdersUniqueIDStartingValue();
                                if (maxrowID!=0){
//                            int initialID=myDB.SelectZeroHotelOrdersUniqueIDStartingValue();
//
//                            if (initialID==0){

                                    maxrowID++;
                                    boolean OrdersInserted=myDB.insertOrderNumsTable(String.valueOf(maxrowID));
                                    boolean ReservationInserted= myDB.insertConfirmTBLHotelReservations(
                                            h.HotelID,
                                            email,
                                            h.HotelRoom,
                                            StartFrom,
                                            EndAt,
                                            TotalPayment,
                                            h.HotelName,
                                            h.ImagePath,
                                            h.URL,
                                            String.valueOf(maxrowID),
                                            String.valueOf(roomPrice)
                                    );
                                    if (ReservationInserted==true&&OrdersInserted==true){
//                            Toast.makeText(getApplicationContext(), "Reservation Confirmed Successfully", Toast.LENGTH_LONG).show();
                                        DialogueSuccessful();
                                        txt_FromDate.setText("");
                                        txt_ToDate.setText("");
                                        txt_total_payment.setText("");
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Did not Insert Data", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }


                    }else {
                        DialogueLoginForReservation();
                    }
                }
            }else {
                Toast.makeText(getApplicationContext(), "Please, Verify Date To After Date From" , Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Total Cash Rate is 0.0" , Toast.LENGTH_LONG).show();
        }
    }


    public void initialValueforHotelOrderUniqueID(){
        int maxrowID=8963;
        try {
//            String Roomnums= spinner.getSelectedItem().toString();
            boolean OrdersInserted=myDB.insertOrderNumsTable(String.valueOf(maxrowID));
            boolean ReservationInserted= myDB.insertConfirmTBLHotelReservations(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "http://empty",
                    String.valueOf(maxrowID),
                    ""
            );
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Not Initialized", Toast.LENGTH_LONG).show();
        }
    }

    public void DialogueSuccessful(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Successfull")
                .setTitle("Hotels Reservation")
                .setPositiveButton("Nice", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

    public void DialogueLoginForReservation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You must Sign in First to confirm your reservation process")
                .setTitle("Attention")
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

//    private int generateMonthinNums(String month_srt) {
//        int month=0;
//        switch (month_srt) {
//            case "Jan":
//                month=1 ;
//                break;
//            case "Feb":
//                month= 2;
//                break;
//            case "Mars":
//                month=3 ;
//                break;
//            case "April":
//                month=4 ;
//                break;
//            case "May":
//                month=5 ;
//                break;
//            case "Jun":
//                month=6 ;
//                break;
//            case "Jul":
//                month=7 ;
//                break;
//            case "Aug":
//                month=8 ;
//                break;
//            case "Sep":
//                month= 9;
//                break;
//            case "Oct":
//                month=10 ;
//                break;
//            case "Nov":
//                month= 11;
//                break;
//            case "Dec":
//                month=12 ;
//                break;
//        }
//        return month;
//    }
}