package android.fullsail.com.javaii_casestudy2.Classes;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shaun on 12/22/2014.
 */

public class WeatherHourly {


    // Descriptive variables
    private String mTime;
    private String mWeather;
    private String mTemp;




    // Class constructor
    public WeatherHourly() {
        mTime = "";
    }
    public WeatherHourly(String _time) {
        mTime = _time;



    }

    public WeatherHourly(JSONObject wData){
        try{
            mTime = wData.getString("pretty");

        } catch (Exception e) {
            Log.e("OBJECT COMPILING ERROR: ", "Error Updating Display");
        }

    }



    // Getter Methods
    public String getTime() {
        return mTime;
    }







    // Setter Methods
    public void setTime(String _time) {
        mTime = _time;
    }






    @Override
    public String toString() {
        return mTime;
    }
}

