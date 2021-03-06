package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class androidlistviewactivity extends AppCompatActivity{

    private final String LOG_TAG = androidlistviewactivity.class.getSimpleName();
    static final String URL="https://cldup.com/6GN4PoElng.json";
    String main_List="Desires",Image_URL="thumb_url",TITLE="title",ID="id";
    String ID_STRING,TITLE_STRING,Image_URL_STRING;
    public static final String PREFS_Logger = "LoggerFile";

    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    LazyAdapter mAdapter;
    ListView listView;

    public JSONObject UsersDesiresJson;
    public JSONArray UsersDesiresJsonAray;
    public JSONObject oneOptionData;

    public String LoggedUser;
    SessionManagement sessionManagement;

    DBHelper myDB;
    ArrayList<UsersEntity> Logger;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);//Menu Resource, Menu
        int state= checkState(LoggedUser);
        if (state==1){
            menu.getItem(0).setTitle("Sign Out").toString();
        }else if (state==0){
            menu.getItem(0).setTitle("Sign In").toString();
        }
        return super.onCreateOptionsMenu(menu);
    }

    public int checkState(String LoggedUser){
        if (LoggedUser!=null){
            return 1;
        }else return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().toString().matches("Sign In")){
            Intent intent_MainActivity = new Intent(getApplicationContext(), MainActivity.class);
            intent_MainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent_MainActivity);
        }else if (item.getTitle().toString().matches("Sign Out")){
            LoggedUser="";
            sessionManagement.logoutUser();
            Intent intent_refresh = new Intent(getApplicationContext(), androidlistviewactivity.class);
            intent_refresh.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent_refresh);
            item.setTitle("Sign In").toString();
        }else if (!item.getTitle().toString().matches("")){
            String itemTitle=item.getTitle().toString();
            Logger=myDB.getLoggedUserInfo(itemTitle);
            Intent intent_LoggerDetails = new Intent(this, Profile.class);
            Bundle b = new Bundle();
            b.putSerializable("LoggerInfo", Logger);
            intent_LoggerDetails.putExtras(b);
            startActivity(intent_LoggerDetails);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
             if (info.position==1){
                 if (v.getId()==R.id.list_main) {
                     menu.setHeaderTitle("Download");
                     String[] menuItem = {"Android app", "iPhone app"};
                     for (int i = 0; i < menuItem.length; i++) {
                         menu.add(menu.NONE, i, i, menuItem[i]);
                     }
                 }
             }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos= info.position;
        switch (pos){
            case 1:
                if (item.getTitle()=="Android app"){
                    Intent intent_UdacityAndroidApp = new Intent();
                    intent_UdacityAndroidApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_UdacityAndroidApp .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.udacity.android&hl=en"));
                    getApplicationContext().startActivity(intent_UdacityAndroidApp );
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_UdacityiPhoneApp = new Intent();
                    intent_UdacityiPhoneApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_UdacityiPhoneApp .setData(Uri.parse("https://itunes.apple.com/us/app/id819700933?mt=8"));
                    getApplicationContext().startActivity(intent_UdacityiPhoneApp );
                }
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(1).setTitle(LoggedUser).toString();
        return super.onPrepareOptionsMenu(menu);
    }
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidlistviewactivity);
        this.setTitle("YourBest TV");
        sessionManagement= new SessionManagement(getApplicationContext());
//        DialogueNote();
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
        try {
            myDB = new DBHelper(this);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Didn't Create Database", e);
        }

        listView=(ListView)findViewById(R.id.list_main);
            if (savedInstanceState != null ) {
//                &&savedInstanceState.getInt("Position")!=0&&savedInstanceState.getInt("top")!=0 // && savedInstanceState.getSerializable("mylist")!=null
                Bundle b = getIntent().getExtras();
                if (b != null) {
                    LoggedUser = b.getString("Email", null);
                }    // get username and set it to action bar

                mAdapter = new LazyAdapter(getApplicationContext(), R.layout.list_row, new ArrayList<OptionsEntity>());
//                mAdapter= (LazyAdapter) savedInstanceState.getSerializable("mylist");
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
//                listView.setSelectionFromTop();
                //--------------------
//                position = savedInstanceState.getInt("Position", 0);
//                top=savedInstanceState.getInt("top",0);
//                if (top < 0 ) {
//                    //&& listView.getChildAt(0) != null
//                    position++;
//                    v = listView.getChildAt(1);
//                    top = v.getTop();
//                    istView.setSelectionFromTop(position,top);
//                }
                //--------------------
//                listView.smoothScrollToPosition(position);
//                listView.setSelection(position);
//                mAdapter.notifyDataSetChanged();
//                listView.clearFocus();
//                listView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        listView.setSelection(position);
////                        listView.smoothScrollToPosition(position);
//                    }
//                });
//                listView.onRestoreInstanceState(state);
            }else {
                Bundle b = getIntent().getExtras();
                if (b != null) {
                    LoggedUser = b.getString("Email", null);
                }        // get username and set it to action bar
                    mAdapter = new LazyAdapter(getApplicationContext(), R.layout.list_row, new ArrayList<OptionsEntity>());
                    listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
// save index and top position
//        int index = listView.getFirstVisiblePosition();
//        View v = listView.getChildAt(0);
//        final int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
//        int top = (v == null) ? 0 : v.getTop() ;
//        if (top<0&& listView.getChildAt(1)!=null){
//            position++;
//            v=listView.getChildAt(1);
//            top=v.getTop();
//
//        }
//        final int finalTop = top;
//        listView.post(new Runnable() {
//            @Override
//            public void run() {
////                listView.setSelectionFromTop(14, finalTop);
//                listView.smoothScrollToPosition(14);
//            }
//        });

//        int index = listView.getFirstVisiblePosition();
//        View v = listView.getChildAt(0);
//        int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
// ...
// restore index and position
//        listView.setSelectionFromTop(index, top);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            Intent intent_Udacity = new Intent(getApplicationContext(), Udacity.class);
                            intent_Udacity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_Udacity);
                            break;
                        case 1:
                            DialogueLongClickItem();
                            break;
                        case 2:
                            Intent intent_Hotels = new Intent(getApplicationContext(), Hotels.class);
                            intent_Hotels.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_Hotels);
                            break;
                        case 3:
                            DialogueItemDisapled();
//                        Intent intent_Aviation = new Intent(getApplicationContext(), aviation.class);
//                        intent_Aviation.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getApplicationContext().startActivity(intent_Aviation);
                            break;
                        case 4:
                            Intent intent_Cars = new Intent(getApplicationContext(), Cars.class);
                            intent_Cars.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_Cars);
                            break;
                        case 5:
                            Intent intent_Burger_King = new Intent();
                            intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
                            getApplicationContext().startActivity(intent_Burger_King);
                            break;
                        case 6:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 7:
                            Intent intent_COOK_DOOR = new Intent();
                            intent_COOK_DOOR.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent_COOK_DOOR.setData(Uri.parse("http://www.cookdoor.com.eg/"));
                            getApplicationContext().startActivity(intent_COOK_DOOR);
                            break;
                        case 8:
                            Intent intent_KFC = new Intent(getApplicationContext(), KFC_ListView.class);
                            intent_KFC.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_KFC);
                            break;
                        case 9:
                            Intent intent_mcDonalds = new Intent();
                            intent_mcDonalds.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent_mcDonalds.setData(Uri.parse("http://corporate.mcdonalds.com/mcd.html"));
                            getApplicationContext().startActivity(intent_mcDonalds);
                            break;
                        case 10:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 11:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 12:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 13:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 14:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 15:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 16:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 17:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 18:
//                        Intent intent_Burger_King = new Intent();
//                        intent_Burger_King.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent_Burger_King.setData(Uri.parse("http://www.burgerking.co.uk/"));
//                        getApplicationContext().startActivity(intent_Burger_King);
                            DialogueItemDisapled();
                            break;
                        case 19:
                            Intent intent_Sports = new Intent(getApplicationContext(), Sports.class);
                            intent_Sports.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_Sports);
                            break;

                        case 20:
                            Intent intent_RecommendedApps = new Intent(getApplicationContext(), RecommendedApps.class);
                            intent_RecommendedApps.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent_RecommendedApps);
                            break;
                        default:
                            break;
                    }
                }
            });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        listView.onRestoreInstanceState(state);
    }

    public void DialogueNote(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("some desires are enabled, some other displays your preferred websites, while the others used via booking and reservation processes, but the most are disabled now.\nSo, remember to respond positivly to our upcoming updates, we are here to help you, we are going to become your TV.\nyourbest-online.com\nThanks")
                .setTitle("Dear valued client")
                .setPositiveButton("bey", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

    public void DialogueItemDisapled(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sorry our valued client, this channel is disabled now, please, download our upcoming release, we are here to help you, we are going to become your TV.\nyourbest-online.com\nThanks")
                .setTitle("Disabled Channel")
                .setPositiveButton("bey", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

    public void DialogueLongClickItem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This element requires a long Click to download.\n\nyourbest-online.com\n")
                .setTitle("Download App")
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startFetchingUsersDesires();
    }

    public void startFetchingUsersDesires() {
        try {
            FetchUsersDesires fetchTrailers = new FetchUsersDesires();
            fetchTrailers.execute(URL);
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute Desires");
        }
    }


    public class FetchUsersDesires extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

        private final String LOG_TAG = FetchUsersDesires.class.getSimpleName();

        private ArrayList<OptionsEntity> getUsersDesiresFromJson(String UsersDesires)
                throws JSONException {

            UsersDesiresJson = new JSONObject(UsersDesires);
            UsersDesiresJsonAray = UsersDesiresJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < UsersDesiresJsonAray.length(); i++) {
                // Get the JSON object representing a movie per each loop
                oneOptionData = UsersDesiresJsonAray.getJSONObject(i);

                ID_STRING = oneOptionData .getString(ID);
                TITLE_STRING = oneOptionData .getString(TITLE);
                Image_URL_STRING = oneOptionData .getString(Image_URL);

                mAdapter=null;
                OptionsEntity entity = new OptionsEntity(Image_URL_STRING, TITLE_STRING);
                list.add(entity);
            }

            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String UsersDesires_JsonSTR = null;

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if (params.length == 0) {
                return null;
            }
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    UsersDesires_JsonSTR = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                UsersDesires_JsonSTR = buffer.toString();
                Log.v(LOG_TAG, "Users Desires String: " + UsersDesires_JsonSTR);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error here Exactly ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getUsersDesiresFromJson(UsersDesires_JsonSTR);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Users Desires from getJsonData method", e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result != null&& getApplicationContext()!=null) {
                mAdapter = new LazyAdapter(getApplicationContext(),R.layout.list_row, result);
                listView.setAdapter(mAdapter);
                list=result;
            }
        }
    }
}