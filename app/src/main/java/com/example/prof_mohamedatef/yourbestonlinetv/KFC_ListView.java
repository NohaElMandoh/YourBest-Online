package com.example.prof_mohamedatef.yourbestonlinetv;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
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

//public class KFC_ListView extends AppCompatActivity implements SearchView.OnQueryTextListener {
public class KFC_ListView extends AppCompatActivity {
    private final String LOG_TAG = Sports.class.getSimpleName();
    static final String Url="https://cldup.com/aRzd9y20j3.json";
    String main_List="KFC",Image_URL="thumb_url",Country="Country",ID="id",City="City",Website="website",Address="Address", postal_code="postal_code",latitude="latitude",langitude="langitude";
    String ID_STRING,Country_STRING,Image_URL_STRING,City_String,WebSite_URL_STRING,postal_code_STRING,Address_STRING,Latitude_STRING,Langitude_STRING;
    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    ListView listView;
    public JSONObject KFCsJson;
    public JSONArray KFCsJsonAray;
    public JSONObject oneKFCData;
    KFCListAdapter mAdapter;

    /*
    search
     */
//    private ListView friendListView;
//    SearchView KFCs_SearchView;
//    private MenuItem searchMenuItem;
//    SearchManager searchManager;


//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        KFCs_SearchView.setSubmitButtonEnabled(true);
//        KFCs_SearchView.setOnQueryTextListener(this);
//        return true;
//    }

////    Menu menu;
//    @Override
////    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
////        this.menu=menu;
//////        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
//      searchManager = (SearchManager)
//                    getSystemService(Context.SEARCH_SERVICE);
//        searchMenuItem = menu.findItem(R.id.search);
//        KFCs_SearchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
//        KFCs_SearchView.setSearchableInfo(searchManager.
//                getSearchableInfo(new ComponentName(getApplicationContext(),KFC_ListView.class)));
////        invalidateOptionsMenu();
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                KFC_ListView.this.finish();
//                KFC_ListView.this.overridePendingTransition(R.anim.class, R.anim.bottom_out);
//                ActivityUtils.hideSoftKeyboard(this);
//
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kfc__list_view);
        this.setTitle("YourBest KFCs");
        listView = (ListView) findViewById(R.id.list_KFC);
        mAdapter = new KFCListAdapter(getApplicationContext(), R.layout.kfc_listitem, new ArrayList<OptionsEntity>());
        if (mAdapter!=null){
            mAdapter.notifyDataSetChanged();
            listView.setAdapter(mAdapter);
            listView.setTextFilterEnabled(true);
            mAdapter.notifyDataSetChanged();
        }else {

            listView.setVisibility(View.GONE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "item: " +position, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        Intent intent_KFCOfficialWebSite = new Intent();
                        intent_KFCOfficialWebSite.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_KFCOfficialWebSite.setData(Uri.parse("https://www.kfc.com/"));
                        getApplicationContext().startActivity(intent_KFCOfficialWebSite);
                        break;
                    case 58:
                        Intent intent_KFC_Details = new Intent(getApplicationContext(), KFC_Details.class);
                        intent_KFC_Details.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent_KFC_Details);
                        break;
                }
            }
        });
    }

    private void FetchKFCsData() {
        FetchKFCsData  f_m_data = new FetchKFCsData  ();
        f_m_data.execute(Url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FetchKFCsData();
    }

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        mAdapter.getFilter().filter(newText);
//        return false;
//    }

//    private ProgressBar mProgress;
//    private int mProgressStatus = 0;
//
//    private Handler mHandler = new Handler();
//
//    public void showProgress(){
////        mProgress = (ProgressBar) findViewById(R.id.progress_bar);
//
//        // Start lengthy operation in a background thread
//        new Thread(new Runnable() {
//            public void run() {
//                while (mProgressStatus < 100) {
////                    mProgressStatus = doWork();
//
//                    // Update the progress bar
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            mProgress.setProgress(mProgressStatus);
//                        }
//                    });
//                }
//            }
//        }).start();
//
//    }


    public class FetchKFCsData   extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {
        private final String LOG_TAG = FetchKFCsData.class.getSimpleName();
        private ArrayList<OptionsEntity> getKFCsFromJson(String KFCsJsonStr)
                throws JSONException {
            KFCsJson = new JSONObject(KFCsJsonStr);
            KFCsJsonAray = KFCsJson.getJSONArray(main_List);
            list.clear();
            for (int i = 0; i < KFCsJsonAray.length(); i++) {
                // Get the JSON object representing a movie per each loop
                oneKFCData = KFCsJsonAray.getJSONObject(i);

                ID_STRING= oneKFCData.getString(ID);
                Image_URL_STRING= oneKFCData.getString(Image_URL);
                Country_STRING=oneKFCData.getString(Country);
                Address_STRING = oneKFCData.getString(Address);
                City_String=oneKFCData.getString(City);
                WebSite_URL_STRING=oneKFCData.getString(Website);
                postal_code_STRING=oneKFCData.getString(postal_code);
                Latitude_STRING=oneKFCData.getString(latitude);
                Langitude_STRING=oneKFCData.getString(langitude);
                mAdapter=null;
                OptionsEntity entity = new OptionsEntity(ID_STRING,Image_URL_STRING,Country_STRING,Address_STRING,City_String,WebSite_URL_STRING,postal_code_STRING,Latitude_STRING,Langitude_STRING);
                list.add(entity);
            }

            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String KFCs_JsonSTR = null;

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
                    KFCs_JsonSTR  = null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                KFCs_JsonSTR  = buffer.toString();

                Log.v(LOG_TAG, "KFCs JSON String: " + KFCs_JsonSTR );
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
                return getKFCsFromJson(KFCs_JsonSTR );
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't get KFCs Data from getJsonData method", e);

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<OptionsEntity> result) {
            if (result != null) {
                mAdapter = null;
                list = result;
                 mAdapter = new KFCListAdapter(getApplicationContext(),R.layout.kfc_listitem, result);
                listView.setAdapter(mAdapter);
//                GridLayoutManager layoutManager=(GridLayoutManager)recyclerView.getLayoutManager();
//                Log.v("MainFragment", Integer.toString(Util.pos));
//                layoutManager.scrollToPosition(Util.pos);
            }
        }
    }
}
