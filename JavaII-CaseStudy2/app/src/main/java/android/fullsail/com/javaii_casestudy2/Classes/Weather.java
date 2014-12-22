package android.fullsail.com.javaii_casestudy2.Classes;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shaun on 12/22/2014.
 */

public class Weather {


    // Descriptive variables
    private String mTime;
    private String mWeather;
    private String mTemp;




    // Class constructor
    public Weather() {
        mTime = mWeather  = mTemp = "";
    }
    public Weather(String _time, String _weather, String _temp) {
        mTime = _time;
        mWeather = _weather;
        mTemp = _temp;


    }

    public Weather(JSONObject wData){
        try{
            mTime = wData.getString("local_time_rfc822");
            mWeather = wData.getString("weather");
            mTemp = wData.getString("temperature_string");

        } catch (Exception e) {
            Log.e("OBJECT COMPILING ERROR: ", "Error Updating Display");
        }

    }



    // Getter Methods
    public String getTime() {
        return mTime;
    }
    public String getWeather() {
        return mWeather;
    }
    public String getTemp() {
        return mTemp;
    }






    // Setter Methods
    public void setTime(String _time) {
        mTime = _time;
    }
    public void setWeather(String _weather) {
        mWeather = _weather;
    }
    public void setTemp(String _temp) {
        mTemp = _temp;
    }





    @Override
    public String toString() {
        return mTime + "\n\n Forecast: " + mWeather + "\nTemperature: " + mTemp;
    }
}

