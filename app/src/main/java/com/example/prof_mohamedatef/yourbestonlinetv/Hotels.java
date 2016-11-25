package com.example.prof_mohamedatef.yourbestonlinetv;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
import java.util.List;

public class Hotels extends AppCompatActivity {
//http://www3.hilton.com/en/index.html
    //http://www.ritzcarlton.com/
private final String LOG_TAG = Sports.class.getSimpleName();

    static final String Url="https://yourbest-online.com/images/xml_files/hotels_booking/hotels.json";
    String main_List="Hotels",Image_URL="thumb_url",TITLE="title",ID="id",Room_Price="Room_Price",Website="website";
    String ID_STRING,TITLE_STRING,Image_URL_STRING,Room_Price_STRING,WebSite_URL_STRING;

    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    RecyclerView recyclerView;

    public JSONObject HotelsJson;
    public JSONArray HotelsJsonAray;
    public JSONObject oneHotelData;

    Hotels_RecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        this.setTitle("YourBest Hotels");
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }




    private void FetchHotelsData() {
        FetchHotelsData  f_m_data = new FetchHotelsData ();
        f_m_data.execute(Url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FetchHotelsData();
    }

    public class FetchHotelsData extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

        private final String LOG_TAG = FetchHotelsData .class.getSimpleName();

        private ArrayList<OptionsEntity> getMovieDataFromJson(String MoviesJsonStr)
                throws JSONException {

            HotelsJson = new JSONObject(MoviesJsonStr);
            HotelsJsonAray = HotelsJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < HotelsJsonAray.length(); i++) {

                // Get the JSON object representing a movie per each loop
                oneHotelData = HotelsJsonAray.getJSONObject(i);

                ID_STRING= oneHotelData.getString(ID);
                Image_URL_STRING= oneHotelData.getString(Image_URL);
                TITLE_STRING = oneHotelData.getString(TITLE);
                Room_Price_STRING=oneHotelData.getString(Room_Price);
                WebSite_URL_STRING=oneHotelData.getString(Website);
                mAdapter=null;
                OptionsEntity entity = new OptionsEntity(Image_URL_STRING,TITLE_STRING,Room_Price_STRING,WebSite_URL_STRING,ID_STRING);
                list.add(entity);
            }

            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String Movies_images_JsonSTR = null;

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
                    Movies_images_JsonSTR = null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                Movies_images_JsonSTR = buffer.toString();

                Log.v(LOG_TAG, "Movies JSON String: " + Movies_images_JsonSTR);
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
                return getMovieDataFromJson(Movies_images_JsonSTR);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Movies Data from getJsonData method", e);

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result != null) {
                mAdapter = null;
                list = result;
                mAdapter = new Hotels_RecyclerAdapter(getApplicationContext(), result);
                recyclerView.setAdapter(mAdapter);
//                GridLayoutManager layoutManager=(GridLayoutManager)recyclerView.getLayoutManager();
//                Log.v("MainFragment", Integer.toString(Util.pos));
//                layoutManager.scrollToPosition(Util.pos);
            }
        }
    }
}
