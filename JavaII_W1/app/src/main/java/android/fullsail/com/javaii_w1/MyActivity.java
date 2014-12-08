package android.fullsail.com.javaii_w1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;



public class MyActivity extends Activity implements MainListFragment.OnListClickListener {

    final String TAG = "DEBUG";

    private boolean connected;
    public TextView input;
    public Button search;
    public String city;
    public URL queryURL;
    final String baseURL = "http://api.wunderground.com/api/5d0eb031ddea63b3/conditions/q/PA/";
    final String extension = ".json";



    // Test network connectivity
    public void getConnection() {

        // Assign context to helper class & run method
        Helper testConnection = new Helper(this);
        connected = testConnection.getConnection();

        Log.i(TAG, "Helper Class Connection: " + connected);

    }

    // assign variables & initiate API request
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        // Run Connection Check method (App Creation)
        getConnection();


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

        // assign local views
        search = (Button) findViewById(R.id.sButton);
        input = (TextView) findViewById(R.id.userInput);


        // event listener for search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getConnection();
                Log.i(TAG, "CONNECTION STATUS ON-SEARCH: " + connected);

                if (connected != false)
                {
                    //grab user input for query
                    city = input.getText().toString();

                    // initiate method to pull from API
                    pullRequest();
                }
                else {

                    // initiate alert
                    AlertDialog.Builder noData = new AlertDialog.Builder(v.getContext());

                    // assign alert fields
                    noData.setTitle("SEARCH CAPABILITY DISABLED");
                    noData.setMessage("Please verify that you have a valid internet connection and try again.");
                    noData.setNeutralButton("Ok", null);

                    AlertDialog dataDialog = noData.create();
                    dataDialog.show();

                    ((TextView) findViewById(R.id.titleView)).setText("No Connection Detected.");

                    // identify local storage path
                    File f = getFilesDir();
                    String path = f.getAbsolutePath();
                    ((TextView) findViewById(R.id.detailView)).setText("\nPossible Local Storage:\n" + path);



                }



            }

        });



    }


    // DISPLAY METHOD - Pulls info from MainListFragment selection
    @Override
    public void displayText(String text) throws IOException {

        // reassign city variable to selected list item
            city = text;
            Log.e(TAG, "CITY = " + city);

        // check data connectivity
        getConnection();

        if (connected != false){

            //  display data from API request
            pullRequest();

        }
        else {

            // Assign context to helper class & run method
            Helper readFile = new Helper(this);

            String details = readFile.readFile(city);

            TextView titleV = (TextView) findViewById(R.id.titleView);
            TextView detailV = (TextView) findViewById(R.id.detailView);

            if (details != null)
            {
                titleV.setText(city);
                detailV.setText(details);
            }
            else if (details.equals(""))
            {
                titleV.setText(city + ".txt Not Found!");
                detailV.setText("Please verify your directory is correct & try again.");
            }







        }


    }



    // ASYNCTASK - API query
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
            Locations result = new Locations(apiData);
            try {
                updateDisplay(result);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }



    private void updateDisplay(Locations location) throws IOException {

        String name = location.getName();
        String info = location.toString();

        if(location.getZip() != null)
        {

            ((TextView) findViewById(R.id.titleView)).setText(name);
            ((TextView) findViewById(R.id.detailView)).setText(info);

            /* -- ORIGINAL CREATE FILE METHOD CALL
            // save information displayed to local storage file
            createFile(name, info);
            */

            // Assign context to helper class & run method
            Helper createFile = new Helper(this);
            createFile.createFile(name, info);

        }

        else {

             ((TextView) findViewById(R.id.titleView)).setText("ERROR!");
            ((TextView) findViewById(R.id.detailView)).setText("\nQuery: " + city +
                    "\nResult: No location found. Please search again.");

            // ORIGINAL READFILE METHOD CALL
            // readFile(name);

            // Assign context to helper class & run method
            Helper readFile = new Helper(this);
            readFile.readFile(name);


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



}


