package com.example.prof_mohamedatef.yourbestonlinetv;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

public class Cars extends Activity {

//    http://www.topspeed.com/
//    http://www.volvocars.com/intl/own/owner-info/stories-and-inspiration/freedom-to-explore
//  https://www.globalcars.com.au/
//    http://www.cardomain.com/makemodel/page-2/
//    https://www.netcarshow.com/
//    http://www.toyota-global.com/showroom/vehicle_gallery/
//http://www.toyota-global.com/showroom/toyota_design/concept_cars/gallery.html
//    http://cargalleryinc.ca/index.php
//    http://www.seriouswheels.com/
//    http://www.toyota.com/landcruiser/

    private final String LOG_TAG = androidlistviewactivity.class.getSimpleName();

    static final String URL="https://yourbest-online.com/images/xml_files/Cars/cars.json";
    String main_List="Cars",Image_URL="thumb_url",TITLE="title",ID="id";
    String ID_STRING,TITLE_STRING,Image_URL_STRING;

    ListView listView;

    public JSONObject CarsJson;
    public JSONArray CarsJsonAray;
    public JSONObject oneCarData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        this.setTitle("YourBest automobile gallery");
        DialogueItemDisapled();
        listView=(ListView)findViewById(R.id.list_Cars);
        mAdapter = new CarsGalleryAdapter (getApplicationContext(),R.layout.list_row, new ArrayList<CarsEntity>());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent_Mercedes = new Intent();
                        intent_Mercedes.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Mercedes.setData(Uri.parse("http://www.mercedes-amg.com/webspecial/sl65blackseries/"));
                        getApplicationContext().startActivity(intent_Mercedes);
                        break;
                    case 1:
                        Intent intent_infiniti = new Intent();
                        intent_infiniti.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_infiniti.setData(Uri.parse("http://www.infiniti.com/eg/"));
                        getApplicationContext().startActivity(intent_infiniti);
                        break;
                    case 2:
                        Intent intent_Toyota = new Intent();
                        intent_Toyota.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Toyota.setData(Uri.parse("http://www.toyota.com/scion"));
                        getApplicationContext().startActivity(intent_Toyota);
                        break;
                    case 3:
                        Intent intent_Volvo = new Intent();
                        intent_Volvo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Volvo.setData(Uri.parse("http://www.volvocars.com/intl/own/owner-info/stories-and-inspiration/freedom-to-explore"));
                        getApplicationContext().startActivity(intent_Volvo);
                        break;
                    case 4:
                        Intent intent_Volexwagen = new Intent();
                        intent_Volexwagen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Volexwagen.setData(Uri.parse("http://www.beetle.com/int/en/home"));
                        getApplicationContext().startActivity(intent_Volexwagen);
                        break;
                    case 5:
                        Intent intent_Mini = new Intent();
                        intent_Mini.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Mini.setData(Uri.parse("https://www.mini.co.uk/en_GB/home.html"));
                        getApplicationContext().startActivity(intent_Mini);
                        break;
                    case 6:
                        Intent intent_Dodge = new Intent();
                        intent_Dodge.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Dodge.setData(Uri.parse("http://www.dodge.com/"));
                        getApplicationContext().startActivity(intent_Dodge);
                        break;
                    case 7:
                        Intent intent_HHONda = new Intent();
                        intent_HHONda.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_HHONda.setData(Uri.parse("http://www.honda.com.br/"));
                        getApplicationContext().startActivity(intent_HHONda);
                        break;
                    case 8:
                        Intent intent_Chevrolet = new Intent();
                        intent_Chevrolet.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Chevrolet.setData(Uri.parse("http://www.chevrolet.com/"));
                        getApplicationContext().startActivity(intent_Chevrolet);
                        break;
                    case 9:
                        Intent intent_alfaromeo = new Intent();
                        intent_alfaromeo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_alfaromeo.setData(Uri.parse("http://www.alfaromeo.com/"));
                        getApplicationContext().startActivity(intent_alfaromeo);
                        break;
                    case 10:
                        Intent intent_MG_Cars = new Intent();
                        intent_MG_Cars.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_MG_Cars.setData(Uri.parse("http://mg.co.uk/"));
                        getApplicationContext().startActivity(intent_MG_Cars);
                        break;
                    case 11:
                        Intent intent_Lexus = new Intent();
                        intent_Lexus.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Lexus.setData(Uri.parse("http://www.lexus-gs450h.ru/"));
                        getApplicationContext().startActivity(intent_Lexus);
                        break;
                    case 12:
                        Intent intent_Audi = new Intent();
                        intent_Audi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Audi.setData(Uri.parse("http://www.audi.com/en.html"));
                        getApplicationContext().startActivity(intent_Audi);
                        break;
                    case 13:
                        Intent intent_BMW = new Intent();
                        intent_BMW.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_BMW.setData(Uri.parse("http://www.bmw.com/com/en/"));
                        getApplicationContext().startActivity(intent_BMW);
                        break;
                    case 14:
                        Intent intent_Chrysler = new Intent();
                        intent_Chrysler.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Chrysler.setData(Uri.parse("http://www.chrysler.com/en/"));
                        getApplicationContext().startActivity(intent_Chrysler);
                        break;
                    case 15:
                        Intent intent_Ford = new Intent();
                        intent_Ford.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Ford.setData(Uri.parse("http://www.ford.com/"));
                        getApplicationContext().startActivity(intent_Ford);
                        break;
                    case 16:
                        Intent intent_GMC = new Intent();
                        intent_GMC.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_GMC.setData(Uri.parse("http://www.gmc.com/"));
                        getApplicationContext().startActivity(intent_GMC);
                        break;
                    case 17:
                        Intent intent_Hyundai = new Intent();
                        intent_Hyundai.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Hyundai.setData(Uri.parse("http://www.hyundai.com/in/en/Main/index.html"));
                        getApplicationContext().startActivity(intent_Hyundai);
                        break;
                    case 18:
                        Intent intent_Mazda = new Intent();
                        intent_Mazda.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Mazda.setData(Uri.parse("http://www.mazda.com/"));
                        getApplicationContext().startActivity(intent_Mazda);
                        break;
                    case 20:
                        Intent intent_Jeep = new Intent();
                        intent_Jeep.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Jeep.setData(Uri.parse("http://www.jeep.com/en/"));
                        getApplicationContext().startActivity(intent_Jeep);
                        break;
                    case 21:
                        Intent intent_citroen = new Intent();
                        intent_citroen.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_citroen.setData(Uri.parse("http://www.citroen.com/en"));
                        getApplicationContext().startActivity(intent_citroen);
                        break;
                    case 22:
                        Intent intent_nissan = new Intent();
                        intent_nissan.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_nissan.setData(Uri.parse("http://www.nissan-global.com/EN/index.html"));
                        getApplicationContext().startActivity(intent_nissan);
                        break;
                    case 23:
                        Intent intent_Opel = new Intent();
                        intent_Opel.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Opel.setData(Uri.parse("http://www.opel.com/vehicles/index.html"));
                        getApplicationContext().startActivity(intent_Opel);
                        break;
                    case 24:
                        Intent intent_Peugeot = new Intent();
                        intent_Peugeot.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Peugeot.setData(Uri.parse("http://www.peugeot.com/en"));
                        getApplicationContext().startActivity(intent_Peugeot);
                        break;
                    case 25:
                        Intent intent_Renault = new Intent();
                        intent_Renault.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Renault.setData(Uri.parse("https://group.renault.com/en/"));
                        getApplicationContext().startActivity(intent_Renault);
                        break;
                    case 26:
                        Intent intent_toyotaracing = new Intent();
                        intent_toyotaracing.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_toyotaracing.setData(Uri.parse("http://www.toyotaracing.com/#/home"));
                        getApplicationContext().startActivity(intent_toyotaracing);
                        break;
                    case 27:
                        Intent intent_codaautomotive = new Intent();
                        intent_codaautomotive.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_codaautomotive.setData(Uri.parse("http://www.codaautomotive.com/"));
                        getApplicationContext().startActivity(intent_codaautomotive);
                        break;
                    case 28:
                        Intent intent_topspeed = new Intent();
                        intent_topspeed.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_topspeed.setData(Uri.parse("http://www.topspeed.com/"));
                        getApplicationContext().startActivity(intent_topspeed);
                        break;
                    case 29:
                        Intent intent_seriousweel = new Intent();
                        intent_seriousweel.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_seriousweel.setData(Uri.parse("http://www.seriouswheels.com/"));
                        getApplicationContext().startActivity(intent_seriousweel);
                        break;
                    case 30:
                        Intent intent_cargalleryinc = new Intent();
                        intent_cargalleryinc.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_cargalleryinc.setData(Uri.parse("http://cargalleryinc.ca/index.php"));
                        getApplicationContext().startActivity(intent_cargalleryinc);
                        break;
                    case 31:
                        Intent intent_cardomain = new Intent();
                        intent_cardomain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_cardomain.setData(Uri.parse("http://www.cardomain.com/makemodel/page-2/"));
                        getApplicationContext().startActivity(intent_cardomain);
                        break;
                    case 32:
                        Intent intent_globalcars = new Intent();
                        intent_globalcars.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_globalcars.setData(Uri.parse("https://www.globalcars.com.au/"));
                        getApplicationContext().startActivity(intent_globalcars);
                        break;
                }
            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startFetchingCars();
    }

    public void DialogueItemDisapled(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Some of the following channels may require Adope Flash Player Installed on your device, if your device support such plugin, don't hesitate to click download link, every thing here are safe, we are here to help you, we are going to become your TV.\nyourbest-online.com\nKind regards, Thanks")
                .setTitle("Attention Please")
                .setPositiveButton("bey", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue=builder.create();
        Dialogue.show();
    }

    public void startFetchingCars() {
        try {
            FetchCars fetchCars = new FetchCars();
            fetchCars .execute(URL);
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute CarsJsonString");
        }
    }

    ArrayList<CarsEntity> list = new ArrayList<CarsEntity>();
    CarsGalleryAdapter mAdapter;

    public class FetchCars extends AsyncTask<String, Void, ArrayList<CarsEntity>> {

        private final String LOG_TAG = FetchCars.class.getSimpleName();

        private ArrayList<CarsEntity> getCarsFromJson(String CarsJsonString)
                throws JSONException {

            CarsJson= new JSONObject(CarsJsonString);
            CarsJsonAray = CarsJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < CarsJsonAray.length(); i++) {

                // Get the JSON object representing a movie per each loop
                oneCarData = CarsJsonAray.getJSONObject(i);

                ID_STRING = oneCarData.getString(ID);
                TITLE_STRING = oneCarData.getString(TITLE);
                Image_URL_STRING = oneCarData.getString(Image_URL);
                mAdapter=null;
                CarsEntity entity = new CarsEntity(Image_URL_STRING, TITLE_STRING);
                list.add(entity);
            }
            return list;
        }

        @Override
        protected ArrayList<CarsEntity> doInBackground(String... params) {
            String Cars_JsonSTR = null;
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
                    Cars_JsonSTR  = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                Cars_JsonSTR  = buffer.toString();

                Log.v(LOG_TAG, "Cars Json String: " + Cars_JsonSTR );
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
                return getCarsFromJson(Cars_JsonSTR);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Cars String from getJsonData method", e);

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<CarsEntity> result) {
            if (result != null&& getApplicationContext()!=null) {
                mAdapter = new CarsGalleryAdapter(getApplicationContext(),R.layout.list_row, result);
                listView.setAdapter(mAdapter);
            }
        }
    }
}