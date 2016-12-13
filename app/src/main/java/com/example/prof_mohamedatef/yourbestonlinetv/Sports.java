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

public class Sports extends AppCompatActivity {

    private final String LOG_TAG = Sports.class.getSimpleName();

    static final String URL="https://cldup.com/DDCyX8usiD.json";
    String main_List="Clubs",Image_URL="thumb_url",TITLE="title",ID="id";
    String ID_STRING,TITLE_STRING,Image_URL_STRING;

    ListView listView;

    public JSONObject TeamsJson;
    public JSONArray TeamsJsonAray;
    public JSONObject oneTeamData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        this.setTitle("YourBest Sports Channels");
        listView=(ListView)findViewById(R.id.list_SporClubs);
        mAdapter = new LazyAdapter(getApplicationContext(),R.layout.list_row, new ArrayList<OptionsEntity>());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent_Barcelona = new Intent();
                        intent_Barcelona.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Barcelona.setData(Uri.parse("https://www.fcbarcelona.com/"));
                        getApplicationContext().startActivity(intent_Barcelona);
                        break;
                    case 1:
                        Intent intent_RealMadrid = new Intent();
                        intent_RealMadrid.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_RealMadrid.setData(Uri.parse("http://www.realmadrid.com/en"));
                        getApplicationContext().startActivity(intent_RealMadrid);
                        break;
                    case 2:
                        Intent intent_InterMilan = new Intent();
                        intent_InterMilan.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_InterMilan.setData(Uri.parse("http://www.inter.it/en/hp"));
                        getApplicationContext().startActivity(intent_InterMilan);
                        break;
                    case 3:
                        Intent intent_ACMilan = new Intent();
                        intent_ACMilan .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_ACMilan .setData(Uri.parse("https://www.acmilan.com/en"));
                        getApplicationContext().startActivity(intent_ACMilan );
                        break;
                    case 4:
                        Intent intent_paris_saint_germain = new Intent();
                        intent_paris_saint_germain .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_paris_saint_germain .setData(Uri.parse("http://www.psg.fr/en/Accueil/0/Home"));
                        getApplicationContext().startActivity(intent_paris_saint_germain );
                        break;
                    case 5:
                        Intent intent_Bayern_Munchein = new Intent();
                        intent_Bayern_Munchein .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Bayern_Munchein .setData(Uri.parse("https://fcbayern.com/en"));
                        getApplicationContext().startActivity(intent_Bayern_Munchein );
                        break;
                    case 6:
                        Intent intent_Arsenal = new Intent();
                        intent_Arsenal.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Arsenal.setData(Uri.parse("http://www.arsenal.com/home"));
                        getApplicationContext().startActivity(intent_Arsenal);
                        break;
                    case 7:
                        Intent intent_Chelsea = new Intent();
                        intent_Chelsea.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Chelsea.setData(Uri.parse("http://www.chelseafc.com/"));
                        getApplicationContext().startActivity(intent_Chelsea);
                        break;
                    case 8:
                        Intent intent_Liverpool = new Intent();
                        intent_Liverpool.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Liverpool.setData(Uri.parse("http://www.liverpoolfc.com/welcome-to-liverpool-fc"));
                        getApplicationContext().startActivity(intent_Liverpool);
                        break;
                    case 9:
                        Intent intent_Manchester_United = new Intent();
                        intent_Manchester_United.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Manchester_United.setData(Uri.parse("http://www.manutd.com/"));
                        getApplicationContext().startActivity(intent_Manchester_United);
                        break;
                    case 10:
                        Intent intent_Al_Ahly = new Intent();
                        intent_Al_Ahly.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Al_Ahly.setData(Uri.parse("http://alahlyegypt.com/"));
                        getApplicationContext().startActivity(intent_Al_Ahly);
                        break;
                    case 11:
                        Intent intent_El_zamalek = new Intent();
                        intent_El_zamalek.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_El_zamalek.setData(Uri.parse("http://el-zamalek.com/"));
                        getApplicationContext().startActivity(intent_El_zamalek);
                        break;
                    case 12:
                        Intent intent_TallaKora = new Intent();
                        intent_TallaKora.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_TallaKora.setData(Uri.parse("http://www.yallakora.com/"));
                        getApplicationContext().startActivity(intent_TallaKora);
                        break;
                    case 13:
                        Intent intent_FillGoal = new Intent();
                        intent_FillGoal.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_FillGoal.setData(Uri.parse("https://www.filgoal.com/"));
                        getApplicationContext().startActivity(intent_FillGoal);
                        break;
                    case 14:
                        Intent intent_Sports = new Intent();
                        intent_Sports.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Sports.setData(Uri.parse("http://www.sports.fr/"));
                        getApplicationContext().startActivity(intent_Sports);
                        break;
                    case 15:
                        Intent intent_Allsport_live = new Intent();
                        intent_Allsport_live.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Allsport_live.setData(Uri.parse("http://allsport-live.net/eng/"));
                        getApplicationContext().startActivity(intent_Allsport_live);
                        break;
                    case 16:
                        Intent intent_Laola_tv = new Intent();
                        intent_Laola_tv.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_Laola_tv.setData(Uri.parse("http://www.laola1.tv/en-int/live-schedule/"));
                        getApplicationContext().startActivity(intent_Laola_tv);
                        break;
                    case 17:
                        Intent intent_espn_tv = new Intent();
                        intent_espn_tv .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_espn_tv .setData(Uri.parse("http://www.espn.com/watchespn/index"));
                        getApplicationContext().startActivity(intent_espn_tv );
                        break;
                    case 18:
                        Intent intent_fromHOT = new Intent();
                        intent_fromHOT.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_fromHOT.setData(Uri.parse("http://www.fromhot.com/"));
                        getApplicationContext().startActivity(intent_fromHOT);
                        break;
                    case 19:
                        Intent intent_sport_Stream_tv = new Intent();
                        intent_sport_Stream_tv.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_sport_Stream_tv.setData(Uri.parse("http://www.sportstream.tv/"));
                        getApplicationContext().startActivity(intent_sport_Stream_tv);
                        break;
                    case 20:
                        Intent intent_football365 = new Intent();
                        intent_football365.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_football365.setData(Uri.parse("http://www.football365.com/"));
                        getApplicationContext().startActivity(intent_football365);
                        break;
                    case 21:
                        Intent intent_skySport = new Intent();
                        intent_skySport.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_skySport.setData(Uri.parse("http://www.skysports.com/football/"));
                        getApplicationContext().startActivity(intent_skySport);
                        break;
                    case 22:
                        Intent intent_TribalFootball = new Intent();
                        intent_TribalFootball.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_TribalFootball.setData(Uri.parse("http://www.tribalfootball.com/"));
                        getApplicationContext().startActivity(intent_TribalFootball);
                        break;
                    case 23:
                        Intent intent_bbc = new Intent();
                        intent_bbc .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_bbc .setData(Uri.parse("http://www.bbc.com/sport/football"));
                        getApplicationContext().startActivity(intent_bbc );
                        break;
                    case 24:
                        Intent intent_thespoiler = new Intent();
                        intent_thespoiler.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_thespoiler.setData(Uri.parse("http://www.thespoiler.co.uk/"));
                        getApplicationContext().startActivity(intent_thespoiler);
                        break;
                    case 25:
                        Intent intent_provenquality = new Intent();
                        intent_provenquality.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_provenquality.setData(Uri.parse("https://provenquality.com/"));
                        getApplicationContext().startActivity(intent_provenquality);
                        break;
                    case 26:
                        Intent intent_soccerBase = new Intent();
                        intent_soccerBase.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_soccerBase.setData(Uri.parse("http://www.soccerbase.com/"));
                        getApplicationContext().startActivity(intent_soccerBase);
                        break;
                    case 27:
                        Intent intent_soccerlens = new Intent();
                        intent_soccerlens.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_soccerlens.setData(Uri.parse("http://soccerlens.com/"));
                        getApplicationContext().startActivity(intent_soccerlens);
                        break;
                    case 28:
                        Intent intent_espnfc = new Intent();
                        intent_espnfc.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent_espnfc.setData(Uri.parse("http://www.espnfc.com/"));
                        getApplicationContext().startActivity(intent_espnfc);
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
        startFetchingSportingClubs();
    }

    public void startFetchingSportingClubs() {
        try {
            FetchSportingClubs fetchSportingClubs = new FetchSportingClubs();
            fetchSportingClubs.execute(URL);
        } catch (Exception e) {
            Log.v(LOG_TAG, "didn't Execute SportingClubs");
        }
    }

    ArrayList<OptionsEntity> list = new ArrayList<OptionsEntity>();
    LazyAdapter mAdapter;

    public class FetchSportingClubs extends AsyncTask<String, Void, ArrayList<OptionsEntity>> {

        private final String LOG_TAG = FetchSportingClubs.class.getSimpleName();

        private ArrayList<OptionsEntity> getTeamsFromJson(String Teams)
                throws JSONException {

            TeamsJson = new JSONObject(Teams);
            TeamsJsonAray = TeamsJson.getJSONArray(main_List);

            list.clear();
            for (int i = 0; i < TeamsJsonAray.length(); i++) {

                // Get the JSON object representing a movie per each loop
                oneTeamData = TeamsJsonAray.getJSONObject(i);

                ID_STRING = oneTeamData .getString(ID);
                TITLE_STRING = oneTeamData .getString(TITLE);
                Image_URL_STRING = oneTeamData.getString(Image_URL);

                mAdapter=null;
                OptionsEntity entity = new OptionsEntity(Image_URL_STRING, TITLE_STRING);
                list.add(entity);
            }

            return list;
        }

        @Override
        protected ArrayList<OptionsEntity> doInBackground(String... params) {

            String Teams_JsonSTR = null;

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
                    Teams_JsonSTR  = null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                Teams_JsonSTR  = buffer.toString();

                Log.v(LOG_TAG, "Teams String: " + Teams_JsonSTR );
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
                return getTeamsFromJson(Teams_JsonSTR );
            } catch (JSONException e) {
                Log.e(LOG_TAG, "didn't got Teams from getJsonData method", e);

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
