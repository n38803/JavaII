package android.fullsail.com.javaii_casestudy2.fragments;

/**
 * Created by Shaun on 12/21/2014.
 */

import android.app.Fragment;
import android.fullsail.com.javaii_casestudy2.R;
import android.fullsail.com.javaii_casestudy2.Classes.Weather;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrentFragment extends Fragment {

    final String TAG = "MAIN FRAG";

    public static int sNumber;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public String city;
    public URL queryURL;
    public String forecast;
    final String baseURL = "http://api.wunderground.com/api/5d0eb031ddea63b3/conditions/q/PA/Philadelphia.json";


    public static CurrentFragment newInstance(String text) {
        CurrentFragment fragment = new CurrentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", text);

        fragment.setArguments(bundle);

        return fragment;
    }

    public CurrentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pullRequest();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView tv = (TextView) rootView.findViewById(R.id.title);
        tv.setText(getArguments().getString("msg"));

        Weather weather = new Weather();





        return rootView;
    }

    // assign variables & initiate API request
    public void pullRequest(){
        // initiate API pull
        try {


            // example URL: http://api.wunderground.com/api/5d0eb031ddea63b3/conditions/q/CA/San_Francisco.json

            queryURL = new URL(baseURL);
            Log.i(TAG, "Success URL: " + queryURL);

            // execute task
            new GetWeatherTask().execute(queryURL);



        } catch (Exception e) {
            Log.e(TAG, "Invalid query for location: " + city + "\nURL: " + queryURL);

        }
    }

    private void updateDisplay(Weather weather) throws IOException {



        TextView body = (TextView) getView().findViewById(R.id.body);
        body.setText(weather.toString());

    }


    // ASYNCTASK - API query
    private class GetWeatherTask extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "ASYNCTASK DEBUGGING";


        @Override
        protected void onPreExecute() {


        }


        @Override
        protected JSONObject doInBackground(URL... urls) {

            Log.i(TAG, "You are now in background computing\nBG URL: " + queryURL);

            String jsonString = "";

            // COLLECT STRING RESPONSE FROM API
            for (URL queryURL : urls) {
                try {

                    // open connection based on https URL assigned above
                    HttpURLConnection connection = (HttpURLConnection) queryURL.openConnection();

                    // pull api info and assign to string object
                    jsonString = IOUtils.toString(connection.getInputStream());

                    Log.i(TAG, "API Pull Success.");
                    break;
                } catch (Exception e) {

                    Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());

                }

                forecast = jsonString;
            }

            Log.i(TAG, "Received Data: " + jsonString);


            // CONVERT API STRING RESPONSE TO JSONOBJECT

            JSONObject apiData;

            try {
                apiData = new JSONObject(jsonString);
                Log.i(TAG, "Object creation Complete");

            } catch (Exception e) {
                Log.e(TAG, "Cannot convert API response to JSON");
                apiData = null;
            }

            try {
                apiData = (apiData != null) ? apiData.getJSONObject("current_observation") : null;
                Log.i(TAG, "API JSON data received: " + apiData.toString());
            } catch (Exception e) {


                Log.e(TAG, "Could not parse data record from response: " + apiData);
                apiData = null;
            }

            return apiData;
        }


        protected void onPostExecute(JSONObject apiData) {


            Log.i(TAG, "You have made it to post execution");

            // this is where you populate your object and push to UI
            Weather result = new Weather(apiData);
            try {
                updateDisplay(result);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }



}