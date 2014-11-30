package android.fullsail.com.javaii_w1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;



public class MyActivity extends Activity implements MainListFragment.OnListClickListener {

    final String TAG = "MAIN_ACTIVITY";

    private boolean result = false;
    public TextView input;
    public Button search;
    public String city;
    public URL queryURL;
    final String baseURL = "http://api.wunderground.com/api/5d0eb031ddea63b3/conditions/q/PA/";
    final String extension = ".json";





    // check connection from Custom Helper
    public boolean checkConnection(CustomConnectivityManager connectionCheck) {
        if (connectionCheck.connected = true) {
            result = true;
            Log.i(TAG, "Connected: " + result);
        } else {
            result = false;
            Log.e(TAG, "Connected: " + result);
        }
        return result;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        // ensure there is no saved instance & initiate fragment views
        if (savedInstanceState == null) {

            // list view fragment
            MainListFragment listfrag = MainListFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.list_Fragment, listfrag, MainListFragment.TAG).commit();

            // setup fragment manager for display view
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();

            // display view fragment
            DisplayFragment displayfrag = DisplayFragment.newInstance();
            trans.replace(R.id.display_fragment, displayfrag, DisplayFragment.TAG);
            trans.commit();
        }




        // TODO: MUST SET PARAMETER TO CHECK CONNECTION




        // assign local views
        search = (Button) findViewById(R.id.sButton);
        input = (TextView) findViewById(R.id.userInput);


        // event listener for search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //grab user input for query
                city = input.getText().toString();

                // initiate method to pull from API
                pullRequest();

            }

        });



    }

    public void pullRequest(){
        // initiate API pull
        try {


            // example URL: http://api.wunderground.com/api/5d0eb031ddea63b3/conditions/q/CA/San_Francisco.json

            queryURL = new URL(baseURL + city + extension);
            Log.i(TAG, "Success URL: " + queryURL);

            // execute task
            new GetWeatherTask().execute(queryURL);

            // reset input
            input.setText("");


        } catch (Exception e) {
            Log.e(TAG, "Invalid query for location: " + city + "\nURL: " + queryURL);

        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





    private void updateDisplay(WeatherLocations weather){

        if(weather.getOverview() != null)
        {
            ((TextView) findViewById(R.id.titleView)).setText((weather.getName()));
            ((TextView) findViewById(R.id.detailView)).setText((
                    weather.getCountry() +
                    "\n\nForecast: " + weather.getOverview() +
                    "\nTemperature: " + weather.getTemp() + " Celsius"

            ));
        }

        else {

            // initiate alert
            AlertDialog.Builder invalid = new AlertDialog.Builder(this);

            // assign alert fields
            invalid.setTitle("INVALID LOCATION SETTINGS");
            invalid.setMessage("Please verify your location search and try again.");
            invalid.setNeutralButton("Ok", null);

            AlertDialog invDialog = invalid.create();
            invDialog.show();

            ((TextView) findViewById(R.id.titleView)).setText("ERROR!");
            ((TextView) findViewById(R.id.detailView)).setText("\nQuery: " + city +
                    "\nResult: No location found. Please search again.");


        }

    }








    // display text from List
    @Override
    public void displayText(String text) {

        // reassign city variable to selected list item
        city = text;

        //  perform API request based on selection
        pullRequest();

    }









    private class GetWeatherTask extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "ASYNCTASK DEBUGGING";


        @Override
        protected void onPreExecute() {


            // TODO - PROGRESS BAR?

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
                apiData = (apiData != null) ? apiData.getJSONObject("current_observation").getJSONObject("display_location") : null;
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
            WeatherLocations result = new WeatherLocations(apiData);
            updateDisplay(result);

        }
    }

}


