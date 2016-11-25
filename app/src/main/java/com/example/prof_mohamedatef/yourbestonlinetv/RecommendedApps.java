package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class RecommendedApps extends Activity {

    private final String LOG_TAG = androidlistviewactivity.class.getSimpleName();

    static final String URL="https://yourbest-online.com/images/xml_files/recommended_apps/recommendedApps.json";

    String main_List="Recommended",Image_URL="thumb_url",TITLE="title",ID="id";
    String ID_STRING,TITLE_STRING,Image_URL_STRING;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    LazyAdapter mAdapter;
    ListView listView;

    public JSONObject RecommendedAppsJson;
    public JSONArray RecommendedAppsJsonAray;
    public JSONObject oneRecommendedAppData;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
//        switch (info.position){
//            case 1:
        if (info.position==0||info.position==1||info.position==2||info.position==3||info.position==4||info.position==5||info.position==6||info.position==7||info.position==8||info.position==9){
            if (v.getId()==R.id.list) {
                menu.setHeaderTitle("Download");
                String[] menuItem = {"Android app", "iPhone app"};
                for (int i = 0; i < menuItem.length; i++) {
                    menu.add(menu.NONE, i, i, menuItem[i]);
                }
            }
        }
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos= info.position;
        switch (pos){
            case 0:
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
                break;
            case 1:
                if (item.getTitle()=="Android app"){
                    Intent intent_facebookAndroidApp = new Intent();
                    intent_facebookAndroidApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_facebookAndroidApp .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana&hl=en"));
                    getApplicationContext().startActivity(intent_facebookAndroidApp );
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_facebookiPhoneApp = new Intent();
                    intent_facebookiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_facebookiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/facebook/id284882215?mt=8"));
                    getApplicationContext().startActivity(intent_facebookiPhoneApp);
                }
                break;
            case 2:
                if (item.getTitle()=="Android app"){
                    Intent intent_facebookAndroidApp = new Intent();
                    intent_facebookAndroidApp .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_facebookAndroidApp .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.twitter.android&hl=en"));
                    getApplicationContext().startActivity(intent_facebookAndroidApp );
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_twitteriPhoneApp = new Intent();
                    intent_twitteriPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_twitteriPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/twitter/id333903271?mt=8"));
                    getApplicationContext().startActivity(intent_twitteriPhoneApp);
                }
                break;
            case 3:
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/instagram/id389801252?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;
            case 4:
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.viber.voip&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/viber/id382617920?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;
            case 5:
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.orca&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/messenger/id454638411?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;

            case 6:
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/whatsapp-messenger/id310633997?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;

            case 7://viber
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.snapchat.android&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/snapchat/id447188370?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;

            case 8:
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=jp.naver.line.android&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/line/id443904275?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;
            case 9:
                if (item.getTitle()=="Android app"){
                    Intent intent_InstagramAndroidApp = new Intent();
                    intent_InstagramAndroidApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramAndroidApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.imo.android.imoim&hl=en"));
                    getApplicationContext().startActivity(intent_InstagramAndroidApp);
                }else if (item.getTitle()=="iPhone app"){
                    Intent intent_InstagramiPhoneApp = new Intent();
                    intent_InstagramiPhoneApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_InstagramiPhoneApp.setData(Uri.parse("https://itunes.apple.com/us/app/imo-free-video-calls-and-chat/id336435697?mt=8"));
                    getApplicationContext().startActivity(intent_InstagramiPhoneApp);
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_apps);

        listView = (ListView) findViewById(R.id.list);
        mAdapter = new LazyAdapter(getApplicationContext(), R.layout.list_row, new ArrayList<OptionsEntity>());
        listView.setAdapter(mAdapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6 || position == 7 || position == 8 || position == 9) {
                    DialogueLongClickItem();
                }

            }
        });


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
        startFetchingRecommendedApps();
    }

    public void startFetchingRecommendedApps() {
        try {
            FetchRecommendedApps fetchTrailers = new FetchRecommendedApps();
            fetchTrailers.execute(URL);
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute Apps");
        }
    }

    public class FetchRecommendedApps extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

        private final String LOG_TAG = FetchRecommendedApps.class.getSimpleName();

        private ArrayList<OptionsEntity> getUsersDesiresFromJson(String UsersDesires)
                throws JSONException {

            RecommendedAppsJson = new JSONObject(UsersDesires);
            RecommendedAppsJsonAray = RecommendedAppsJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < RecommendedAppsJsonAray.length(); i++) {
                // Get the JSON object representing a movie per each loop
                oneRecommendedAppData= RecommendedAppsJsonAray.getJSONObject(i);

                ID_STRING = oneRecommendedAppData.getString(ID);
                TITLE_STRING = oneRecommendedAppData.getString(TITLE);
                Image_URL_STRING = oneRecommendedAppData.getString(Image_URL);

                mAdapter=null;
                OptionsEntity entity = new OptionsEntity(Image_URL_STRING, TITLE_STRING);
                list.add(entity);
            }

            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String RecommendedApps_JsonSTR = null;

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if (params.length == 0) {
                return null;
            }
            try {
                java.net.URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    RecommendedApps_JsonSTR = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                RecommendedApps_JsonSTR  = buffer.toString();
                Log.v(LOG_TAG, "Recommended Apps String: " + RecommendedApps_JsonSTR );
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
                return getUsersDesiresFromJson(RecommendedApps_JsonSTR);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Recommended Apps from getJsonData method", e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result != null&& getApplicationContext()!=null) {
                mAdapter = new LazyAdapter(getApplicationContext(),R.layout.list_row, result);
                listView.setAdapter(mAdapter);
            }
        }
    }
}