package android.fullsail.com.javaii_casestudy2.Classes;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shaun on 12/22/2014.
 */

public class WeatherWeekly {


    // Descriptive variables
    private String mTitle;
    private String mTemp;




    // Class constructor
    public WeatherWeekly() {
        mTitle = "";
        mTemp = "";
    }
    public WeatherWeekly(String _title, String _temp) {
        mTitle = _title;
        mTemp = _temp;



    }

    public WeatherWeekly(JSONObject wData){
        try{
            mTitle = wData.getString("pretty");
            mTemp = wData.getString("fcttext");

        } catch (Exception e) {
            Log.e("OBJECT COMPILING ERROR: ", "Error Updating Display");
        }

    }



    // Getter Methods
    public String getTitle() {
        return mTitle;
    }
    public String getTemp() {
        return mTemp;
    }







    // Setter Methods
    public void setTitle(String _title) {
        mTitle = _title;
    }
    public void setTemp(String _temp) {
        mTemp = _temp;
    }






    @Override
    public String toString() {
        return mTitle + "\n" + mTemp;
    }
}

