package android.fullsail.com.javaii_w1;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shaun on 11/30/2014.
 */
public class WeatherLocations {

    // Descriptive variables
    private String mName;
    private String mCountry;
    private String mTemp;
    private String mOverview;



    // Class constructor
    public WeatherLocations() {
        mName = mCountry  = mTemp = mOverview = "";
    }
    public WeatherLocations(String _name, String _country, String _temp, String _overview) {
        mName = _name;
        mCountry = _country;
        mTemp = _temp;
        mOverview = _overview;

    }

    public WeatherLocations(JSONObject wData){
        try{
            mName = wData.getString("city");
            mCountry = wData.getString("state");
            mTemp = wData.getString("full");
            mOverview = wData.getString("zip");
        } catch (Exception e) {
            Log.e("OBJECT COMPILING ERROR: ", "Error Updating Display");
        }

    }



    // Getter Methods
    public String getName() {
        return mName;
    }
    public String getCountry() {
        return mCountry;
    }
    public String getTemp() {
        return mTemp;
    }
    public String getOverview() {
        return mOverview;
    }





    // Setter Methods
    public void setName(String _name) {
        mName = _name;
    }
    public void setCountry(String _country) {
        mCountry = _country;
    }
    public void setTemp(String _temp) {
        mTemp = _temp;
    }
    public void setOverview(String _overview) {
        mOverview = _overview;
    }




    @Override
    public String toString() {
        return "Name: " + mName + "\n Country: " + mCountry + "\nTemperature: " + mTemp + "\nOverview: " + mOverview;
    }



}
