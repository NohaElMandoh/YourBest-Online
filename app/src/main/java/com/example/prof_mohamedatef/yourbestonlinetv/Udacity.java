package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.UserDataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Udacity extends AppCompatActivity {


    private final String LOG_TAG = Udacity.class.getSimpleName();

    static final String URL="https://cldup.com/mgSdL4SyDq.json";

    String main_List="Udacity",Image_URL="thumb_url",TITLE="title",WebSite="website";
    String website_STRING,TITLE_STRING,Image_URL_STRING;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    UdacityNanoListAdapter mAdapter;
    ListView listView;

    public JSONObject UdacityNanoDegreeJson;
    public JSONArray UdacityNanoDegreeJsonAray;
    public JSONObject oneUdacityNanoDegreeData;

    OptionsEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udacity);

        listView=(ListView)findViewById(R.id.Udacitylist);
        mAdapter = new UdacityNanoListAdapter(getApplicationContext(),R.layout.udacity_list, new ArrayList<OptionsEntity>());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent_AIEngineer = new Intent();
                        intent_AIEngineer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_AIEngineer.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_AIEngineer);
                        break;
                    case 1:
                        Intent intent_VRDeveloper = new Intent();
                        intent_VRDeveloper.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_VRDeveloper.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_VRDeveloper);
                        break;
                    case 2:
                        Intent intent_SelfDrivingCarEng = new Intent();
                        intent_SelfDrivingCarEng.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_SelfDrivingCarEng.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_SelfDrivingCarEng);
                        break;
                    case 3:
                        Intent intent_ProductiveAnalytics = new Intent();
                        intent_ProductiveAnalytics.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_ProductiveAnalytics.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_ProductiveAnalytics);
                        break;
                    case 4:
                        Intent intent_AndroidBasics = new Intent();
                        intent_AndroidBasics.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_AndroidBasics.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_AndroidBasics);
                        break;
                    case 5:
                        Intent intent_MachineLearningEngineer = new Intent();
                        intent_MachineLearningEngineer .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_MachineLearningEngineer .setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_MachineLearningEngineer );
                        break;
                    case 6:
                        Intent intent_IntroToProgramming = new Intent();
                        intent_IntroToProgramming .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_IntroToProgramming .setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_IntroToProgramming );
                        break;
                    case 7:
                        Intent intent_FrontEndWebDeveloper = new Intent();
                        intent_FrontEndWebDeveloper .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_FrontEndWebDeveloper .setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_FrontEndWebDeveloper );
                        break;
                    case 8:
                        Intent intent_FullStackWebDeveloper= new Intent();
                        intent_FullStackWebDeveloper.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_FullStackWebDeveloper.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_FullStackWebDeveloper);
                        break;
                    case 9:
                        Intent intent_AndroidDeveloper = new Intent();
                        intent_AndroidDeveloper .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_AndroidDeveloper .setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_AndroidDeveloper );
                        break;
                    case 10:
                        Intent intent_iOSDeveloper = new Intent();
                        intent_iOSDeveloper.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_iOSDeveloper.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_iOSDeveloper);
                        break;
                    case 11:
                        Intent intent_DataAnalyst = new Intent();
                        intent_DataAnalyst.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_DataAnalyst.setData(Uri.parse(list.get(position).getWebsiteURL()));
                        getApplicationContext().startActivity(intent_DataAnalyst);
                        break;
                    default:
                        break;
                }
            }
        });


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

            UdacityNanoDegreeJson= new JSONObject(UsersDesires);
            UdacityNanoDegreeJsonAray= UdacityNanoDegreeJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < UdacityNanoDegreeJsonAray.length(); i++) {

                // Get the JSON object representing a movie per each loop
                oneUdacityNanoDegreeData = UdacityNanoDegreeJsonAray.getJSONObject(i);
                website_STRING=oneUdacityNanoDegreeData.getString(WebSite);
                TITLE_STRING = oneUdacityNanoDegreeData.getString(TITLE);
                Image_URL_STRING = oneUdacityNanoDegreeData.getString(Image_URL);

                mAdapter=null;
                entity = new OptionsEntity(Image_URL_STRING,website_STRING, TITLE_STRING);
                list.add(entity);
            }

            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String Udacity_JsonSTR = null;

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
                    Udacity_JsonSTR  = null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                Udacity_JsonSTR  = buffer.toString();

                Log.v(LOG_TAG, "Udacity Json String: " + Udacity_JsonSTR );
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
                return getUsersDesiresFromJson(Udacity_JsonSTR );
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Udacity Nano Degree from getJsonData method", e);

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result != null&& getApplicationContext()!=null) {
                mAdapter = new UdacityNanoListAdapter(getApplicationContext(),R.layout.udacity_list, result);
                list=result;
                listView.setAdapter(mAdapter);
            }
        }
    }
}