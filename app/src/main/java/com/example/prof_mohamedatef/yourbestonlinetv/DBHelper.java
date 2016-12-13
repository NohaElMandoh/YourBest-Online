package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.VoiceInteractor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Prof-Mohamed Atef on 10/1/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="YourBestWeb.db";
    public static final String TABLE_NAME="Users";
    private static final String TABLE_Hotel_Rooms= "Hotel_Rooms";
    private static final String TABLE_Hotel_Rooms_Price= "Hotel_Rooms_Price";
    private static final String TABLE_Hotel_Rooms_Ordering= "TABLE_Hotel_Rooms_Ordering";

    private static final String TABLE_Hotel_Rooms_Reservation= "Hotel_Rooms_Reservation";

    //// Columns of HotelRooms Table
    private static final String HotelRooms_ID = "id";
    private static final String HotelRooms_AvailableNums = "nums";
    private static final String Hotel_Rooms_Forien_Unique_id = "Hotel_Rooms_Reservation_Forien_Unique_id";

    //// Columns of HotelRooms_Price Table
    private static final String HotelRooms_ID_TBL_Price = "id";
    private static final String HotelRooms_Price = "Price";
    private static final String Hotel_Rooms_Reservation_Unique_Forien_id = "Hotel_Rooms_Reservation_Forien_Unique_id";

    //// Columns of HotelRoomsOrdering Table
    private static final String HotelRoomsOrdering_ID = "ID";
    private static final String HotelRoomsOrdering_RoomNums = "OrdersNums";

    //Columns of Hotel_Rooms_Reservation Table
    private static final String Hotel_Rooms_Reservation_id  = "id";
    private static final String Hotel_Unique_id  = "Hotel_Unique_id";
    private static final String Hotel_Rooms_Reservation_ByUserName  = "ByUserName";
    private static final String Hotel_Rooms_Reservation_Num  = "RoomNum";
    private static final String Hotel_Rooms_Reservation_DateFrom  = "DateFrom";
    private static final String Hotel_Rooms_Reservation_DateTo  = "DateTo";
    private static final String Hotel_Rooms_Reservation_Total  = "Total";
    private static final String Hotel_Name  = "Hotel_Name";
    private static final String Hotel_ImagePath  = "Hotel_ImagePath";
    private static final String Hotel_website  = "Hotel_website";
    private static final String Hotel_RoomPrice  = "Hotel_RoomPrice";
    private static final String HotelRoomsOrdering_Unique_Forien_id  = "HotelRoomsOrdering_Unique_Forien_id";


    // Columns of Users Table
    public static final String COL_1="ID";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Email";
    public static final String COL_5="UserType";
    public static final String COL_6="Gender";
    public static final String COL_7="Password";
    public static final String COL_8="ConfirmPassword";
    public static final String COL_9="DateOfBirth";
    public static final String COL_10="IS_Admin";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null , 2);
        SQLiteDatabase Db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, FirstName TEXT, LastName TEXT, Email TEXT,UserType TEXT, Gender TEXT, Password TEXT, ConfirmPassword TEXT, DateOfBirth TEXT,IS_Admin TEXT) ");
//            db.execSQL("create table "+TABLE_Hotel_Rooms+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, nums TEXT, Hotel_Rooms_Reservation_Forien_Unique_id TEXT) ");
//           db.execSQL("create table "+TABLE_Hotel_Rooms_Price+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Price TEXT, Hotel_Rooms_Reservation_Forien_Unique_id TEXT) ");
            db.execSQL("create table "+TABLE_Hotel_Rooms_Ordering+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, OrdersNums TEXT) ");
            db.execSQL("create table "+TABLE_Hotel_Rooms_Reservation+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Hotel_Unique_id TEXT, ByUserName TEXT, RoomNum TEXT,DateFrom TEXT, DateTo TEXT, Total TEXT, Hotel_Name TEXT, Hotel_ImagePath TEXT, Hotel_website TEXT,HotelRoomsOrdering_Unique_Forien_id TEXT, Hotel_RoomPrice TEXT) ");
        }catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Hotel_Rooms);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Hotel_Rooms_Price);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Hotel_Rooms_Ordering);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Hotel_Rooms_Reservation);
        onCreate(db);
    }

    public boolean insertData(String FirstName ,String LastName ,String Email, String UserType ,String Gender ,String Password,String ConfirmPassword ,String DateOfBirth,String IS_Admin){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues content_values=new ContentValues();

        content_values.put(COL_2,FirstName);
        content_values.put(COL_3,LastName);
        content_values.put(COL_4,Email);
        content_values.put(COL_5,UserType);
        content_values.put(COL_6,Gender);
        content_values.put(COL_7,Password);
        content_values.put(COL_8,ConfirmPassword);
        content_values.put(COL_9,DateOfBirth);
        content_values.put(COL_10,IS_Admin);

        long result= db.insert(TABLE_NAME,null,content_values);

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public boolean RegesterationinsertData(String FirstName ,String LastName ,String Email, String UserType ,String Gender ,String Password,String ConfirmPassword ,String DateOfBirth,String IS_Admin){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues content_values=new ContentValues();

        content_values.put(COL_2,FirstName);
        content_values.put(COL_3,LastName);
        content_values.put(COL_4,Email);
        content_values.put(COL_5,UserType);
        content_values.put(COL_6,Gender);
        content_values.put(COL_7,Password);
        content_values.put(COL_8,ConfirmPassword);
        content_values.put(COL_9,DateOfBirth);
        content_values.put(COL_10,IS_Admin);

        long result= db.insert(TABLE_NAME,null,content_values);

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public boolean checkEmailPassword(String Email,String Password){

        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery="Select Email from \"+TABLE_NAME+\" where Email = ?";
        //Cursor res=db.rawQuery("Select Email from "+TABLE_NAME+" where Email = ?"+Email,null);
        Cursor res=db.rawQuery(selectQuery,new String[]{Email});
//        Cursor res2=db.rawQuery("select Password from "+TABLE_NAME+" where Password = "+Password,null);

        if (res.moveToNext()){//&&res2.moveToNext()){
            return true;
        }
        else return false;
    }

    public boolean Login(String username,String password)
    {
        try
        {
            SQLiteDatabase db=this.getReadableDatabase();
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("select * from Users where Email =" + "\""+ username.trim() + "\""+" and Password="+ "\""+ password.trim() + "\""+"", null);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            if (i>=1){
                return true;
            }else {
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }



    public Cursor cursorHolder(String Username){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] required = {Hotel_Name, Hotel_ImagePath};
        String[] selectionArgs = new String[]{ Username};

        Cursor res = db.query(TABLE_Hotel_Rooms_Reservation, required, null,selectionArgs,null,null,null,null);
        return res;
    }

    public Cursor getCursor(String Username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_Hotel_Rooms_Reservation + " where ByUserName = ? ", new String[]{Username});
//            String selection = Hotel_Rooms_Reservation_ByUserName + " =?";
//            String[] selectionArgs = new String[]{ Username};
//            Cursor res=db.query(TABLE_Hotel_Rooms_Reservation,null,selection,selectionArgs,null,null,null);
        if (res.moveToNext()) {
            do {
                HotelEntity hotelEntity = new HotelEntity();
                hotelEntity.setHotelID(res.getString(res.getColumnIndex("Hotel_Unique_id")));
                hotelEntity.setRoomNum(res.getString(res.getColumnIndex("RoomNum")));
                hotelEntity.setDateFrom(res.getString(res.getColumnIndex("DateFrom")));
                hotelEntity.setDateTo(res.getString(res.getColumnIndex("DateTo")));
                hotelEntity.setTotal(res.getString(res.getColumnIndex("Total")));
                hotelEntity.setHotel_Name(res.getString(res.getColumnIndex("Hotel_Name")));
                hotelEntity.setHotel_ImagePath(res.getString(res.getColumnIndex("Hotel_ImagePath")));
                hotelEntity.setHotel_website(res.getString(res.getColumnIndex("Hotel_website")));


            } while (res.moveToNext());
        }
        res.close();
        return  res;
    }


    public ArrayList<HotelEntity> getHotelsOrderedDataByUserName(String Username) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HotelEntity> HotelArray=new ArrayList<HotelEntity>();

        try {
//            Cursor res = db.rawQuery("select id, ByUserName, RoomNum, DateFrom, DateTo, Total, Hotel_Name, Hotel_ImagePath, Hotel_website from " + TABLE_Hotel_Rooms_Reservation + " where ByUserName = ? ", new String[]{Username});
            Cursor res = db.rawQuery("select * from " + TABLE_Hotel_Rooms_Reservation + " where ByUserName = ? ", new String[]{Username});
//            String selection = Hotel_Rooms_Reservation_ByUserName + " =?";
//            String[] selectionArgs = new String[]{ Username};
//            Cursor res=db.query(TABLE_Hotel_Rooms_Reservation,null,selection,selectionArgs,null,null,null);
            if (res.moveToNext()) {
                do {
                    HotelEntity hotelEntity = new HotelEntity();
                    hotelEntity.setHotelID(res.getString(res.getColumnIndex("Hotel_Unique_id")));
                    hotelEntity.setByUserName(res.getString(res.getColumnIndex("ByUserName")));
                    hotelEntity.setRoomNum(res.getString(res.getColumnIndex("RoomNum")));
                    hotelEntity.setDateFrom(res.getString(res.getColumnIndex("DateFrom")));
                    hotelEntity.setDateTo(res.getString(res.getColumnIndex("DateTo")));
                    hotelEntity.setTotal(res.getString(res.getColumnIndex("Total")));
                    hotelEntity.setHotel_Name(res.getString(res.getColumnIndex("Hotel_Name")));
                    hotelEntity.setHotel_ImagePath(res.getString(res.getColumnIndex("Hotel_ImagePath")));
                    hotelEntity.setHotel_website(res.getString(res.getColumnIndex("Hotel_website")));
                    hotelEntity.setHotelRoomsOrderID(res.getString(res.getColumnIndex("HotelRoomsOrdering_Unique_Forien_id")));
                    hotelEntity.setRoomPrice(res.getString(res.getColumnIndex("Hotel_RoomPrice")));
                    HotelArray.add(hotelEntity);
                } while (res.moveToNext());
            }
//            getCursor(res);
            res.close();
            return  HotelArray;
        } catch (Exception e) {
            return HotelArray;
        }
    }

//    Cursor curs;
//    public void getCursor(Cursor cursor){
//        this.curs=cursor;
//    }
    public UsersEntity UserIdentity(String Email, String Password) {
        SQLiteDatabase db = this.getReadableDatabase();
        UsersEntity usersEntity = new UsersEntity();
        try {
            Cursor res = db.rawQuery("select FirstName, UserType, Email from " + TABLE_NAME + " where Email = ? and Password = ? ", new String[]{Email, Password});
            if (res.moveToNext()) {
                do {

                    usersEntity.setFirstName(res.getString(res.getColumnIndex("FirstName")));
                    usersEntity.setSelectedRadio_UserType(res.getString(res.getColumnIndex("UserType")));
                    usersEntity.setEmail(res.getString(res.getColumnIndex("Email")));
                } while (res.moveToNext());
            }
            res.close();
            return  usersEntity;
        } catch (Exception e) {
            return usersEntity;
        }
    }

    public boolean clearOrdersInTBLHotelReservations(String UserName){
        SQLiteDatabase db = this.getWritableDatabase();
//        "delete * from "+TABLE_Hotel_Rooms_Reservation+"where ByUserName = ? ", new String[]{UserName}
//        db.execSQL("DELETE FROM "+TABLE_Hotel_Rooms_Reservation+" WHERE "+ );
//        " where ByUserName = ? ", new String[]{Username});
        return db.delete(TABLE_Hotel_Rooms_Reservation," ByUserName = ? ", new String[]{UserName})>0;
    }
    /**
     * Inserting new HotelRoomsNums into TABLE_Hotel_Rooms table
     * */
    public boolean insertConfirmTBLHotelReservations (String HotelUniqueID,String User,String RoomsNums,String DateFrom,String DateTo,String Total,String HotelName,String HotelImagePath,String HotelwebSite,String HotelRoomsOrderingnums_Unique_Forienid,String roomprice){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Hotel_Unique_id, HotelUniqueID);
        contentValues.put(Hotel_Rooms_Reservation_ByUserName, User);
        contentValues.put(Hotel_Rooms_Reservation_Num, RoomsNums);
        contentValues.put(Hotel_Rooms_Reservation_DateFrom, DateFrom);
        contentValues.put(Hotel_Rooms_Reservation_DateTo, DateTo);
        contentValues.put(Hotel_Rooms_Reservation_Total, Total);
        contentValues.put(Hotel_Name, HotelName);
        contentValues.put(Hotel_ImagePath, HotelImagePath);
        contentValues.put(Hotel_website, HotelwebSite);
        contentValues.put(HotelRoomsOrdering_Unique_Forien_id, HotelRoomsOrderingnums_Unique_Forienid);
        contentValues.put(Hotel_RoomPrice, roomprice);

        long result=db.insert(TABLE_Hotel_Rooms_Reservation, null, contentValues);

        db.close();

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public boolean insertTBLHotelRoomsNums(String Nums,String HotelForeignKeyID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HotelRooms_AvailableNums, Nums);
        contentValues.put(Hotel_Rooms_Forien_Unique_id, HotelForeignKeyID);
        long result=db.insert(TABLE_Hotel_Rooms_Reservation, null, contentValues);

        db.close();

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public boolean insertTBLHotelRoomsPrices(String Price,String HotelForeignKeyID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HotelRooms_Price, Price);
        contentValues.put(Hotel_Rooms_Reservation_Unique_Forien_id , HotelForeignKeyID);
        long result=db.insert(TABLE_Hotel_Rooms_Reservation, null, contentValues);

        db.close();

        if (result==-1){
            return false;
        }
        else
            return true;
    }

    public boolean insertOrderNumsTable(String OrderNmus){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HotelRoomsOrdering_RoomNums, OrderNmus);

        long result=db.insert(TABLE_Hotel_Rooms_Ordering, null, contentValues);

        db.close();

        if (result==-1){
            return false;
        }
        else
            return true;
    }


    public int getHotelOrderingTBL_MAX_OrderSid(String Email){
        //SELECT * FROM table ORDER BY column DESC LIMIT 1;
        SQLiteDatabase db=this.getWritableDatabase();
        String []required={HotelRoomsOrdering_RoomNums};
        int maxiID=0;
        try {
//            Cursor res = db.rawQuery("select id, ByUserName, RoomNum, DateFrom, DateTo, Total, Hotel_Name, Hotel_ImagePath, Hotel_website from " + TABLE_Hotel_Rooms_Reservation + " where ByUserName = ? ", new String[]{Username});
//            Cursor res = db.rawQuery("SELECT * FROM "+TABLE_Hotel_Rooms_Reservation+" ORDER BY HotelRoomsOrdering_Unique_Forien_id DESC LIMIT 1 Where ByUserName = ? ", new String[]{Email});
            Cursor res = db.rawQuery("SELECT * FROM "+TABLE_Hotel_Rooms_Reservation+" Where ByUserName = ? ORDER BY HotelRoomsOrdering_Unique_Forien_id DESC LIMIT 1 ", new String[]{Email});
//---------
//            Cursor res = db.query(TABLE_Hotel_Rooms_Ordering,required , null, null, null, null, null +" DESC", "1");
//------------
//            String selection = Hotel_Rooms_Reservation_ByUserName + " =?";
//            String[] selectionArgs = new String[]{ Username};
//            Cursor res=db.query(TABLE_Hotel_Rooms_Reservation,null,selection,selectionArgs,null,null,null);
//            String query = "SELECT MAX(OrdersNums) AS OrdersNums FROM "+TABLE_Hotel_Rooms_Reservation+" Where ByUserName = ? ", new String[]{Username};;
//            Cursor res=db.rawQuery("SELECT MAX(HotelRoomsOrdering_Unique_Forien_id) AS OrdersNums FROM "+TABLE_Hotel_Rooms_Reservation+" Where ByUserName = ? ", new String[]{Email});
            if (res.moveToNext()) {
                do {
                    HotelEntity hotelEntity = new HotelEntity();
                    hotelEntity.setHotelRoomsOrderID(res.getString(res.getColumnIndex("HotelRoomsOrdering_Unique_Forien_id")));
                    maxiID=Integer.parseInt(hotelEntity.getHotelRoomsOrderID());
                } while (res.moveToNext());
            }
//            getCursor(res);
            res.close();
        } catch (Exception e) {
        }
        return maxiID;
    }

    public int SelectZeroHotelOrdersUniqueIDStartingValue(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * from "+TABLE_Hotel_Rooms_Reservation,null);
        int maxID=0;
        try{
            if (res.moveToNext()){
                do {
                    HotelEntity hotelEntity = new HotelEntity();
                    hotelEntity.setHotelRoomsOrderID(res.getString(res.getColumnIndex("HotelRoomsOrdering_Unique_Forien_id")));
                    maxID=Integer.parseInt(hotelEntity.getHotelRoomsOrderID());
                } while (res.moveToNext());
            }
            return maxID;
        }catch (Exception e){
            return -1;
        }
    }

//    // Columns of Users Table
//    public static final String COL_1="ID";
//    public static final String COL_2="FirstName";
//    public static final String COL_3="LastName";
//    public static final String COL_4="Email";
//    public static final String COL_5="UserType";
//    public static final String COL_6="Gender";
//    public static final String COL_7="Password";
//    public static final String COL_8="ConfirmPassword";
//    public static final String COL_9="DateOfBirth";
//    public static final String COL_10="IS_Admin";

    public UsersEntity getEmailNoDuplicationConfirmation(String Email){
        SQLiteDatabase db = this.getReadableDatabase();
        UsersEntity usersEntity = new UsersEntity();
//        ArrayList<UsersEntity> UserArr=new ArrayList<UsersEntity>();
        try {
            Cursor res = db.rawQuery("select Email from " + TABLE_NAME + " where Email = ? ", new String[]{Email});
            if (res.moveToNext()) {
                do {
                    usersEntity.setEmail(res.getString(res.getColumnIndex("Email")));
//                    UserArr.add(usersEntity);
                } while (res.moveToNext());
            }
            res.close();
            return  usersEntity;
        } catch (Exception e) {
            return usersEntity;
        }
    }

    public ArrayList<UsersEntity> getLoggedUserInfo(String LoggedUser){
        SQLiteDatabase db = this.getReadableDatabase();
        UsersEntity usersEntity = new UsersEntity();
        ArrayList<UsersEntity> UserArr=new ArrayList<UsersEntity>();
        try {
            Cursor res = db.rawQuery("select FirstName, LastName, Email, UserType, Gender, DateOfBirth, IS_Admin from " + TABLE_NAME + " where Email = ? ", new String[]{LoggedUser});
            if (res.moveToNext()) {
                do {
                    usersEntity.setFirstName(res.getString(res.getColumnIndex("FirstName")));
                    usersEntity.setLastName(res.getString(res.getColumnIndex("LastName")));
                    usersEntity.setEmail(res.getString(res.getColumnIndex("Email")));
                    usersEntity.setSelectedRadio_UserType(res.getString(res.getColumnIndex("UserType")));
                    usersEntity.setSELECTEDGender(res.getString(res.getColumnIndex("Gender")));
                    usersEntity.setDATEOFBIRTH(res.getString(res.getColumnIndex("DateOfBirth")));
                    usersEntity.setISADMIN(res.getString(res.getColumnIndex("IS_Admin")));
                    UserArr.add(usersEntity);
                } while (res.moveToNext());
            }
            res.close();
            return  UserArr;
        } catch (Exception e) {
            return UserArr;
        }
    }

    public boolean UpdateLoggedUSerInfoData(String Email,String FirstName,String LastName,String DateOfBirth){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content_value=new ContentValues();
        content_value.put(COL_2,FirstName);
        content_value.put(COL_3,LastName);
        content_value.put(COL_9, DateOfBirth);
        db.update("Users", content_value,"Email = ?",new String[] { Email });
        return true;
    }
}