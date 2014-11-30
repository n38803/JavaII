package android.fullsail.com.javaii_w1;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Shaun on 11/30/2014.
 */
public class Locations {


    // Descriptive variables
    private String mName;
    private String mState;
    private String mFullName;
    private String mZip;



    // Class constructor
    public Locations() {
        mName = mState  = mFullName = mZip = "";
    }
    public Locations(String _name, String _state, String _full, String _zip) {
        mName = _name;
        mState = _state;
        mFullName = _full;
        mZip = _zip;

    }

    public Locations(JSONObject wData){
        try{
            mName = wData.getString("city");
            mState = wData.getString("state_name");
            mFullName = wData.getString("full");
            mZip = wData.getString("zip");
        } catch (Exception e) {
            Log.e("OBJECT COMPILING ERROR: ", "Error Updating Display");
        }

    }



    // Getter Methods
    public String getName() {
        return mName;
    }
    public String getState() {
        return mState;
    }
    public String getFull() {
        return mFullName;
    }
    public String getZip() {
        return mZip;
    }





    // Setter Methods
    public void setName(String _name) {
        mName = _name;
    }
    public void setState(String _state) {
        mState = _state;
    }
    public void setFull(String _full) {
        mFullName = _full;
    }
    public void setZip(String _zip) {
        mZip = _zip;
    }




    @Override
    public String toString() {
        return mState + "\n\n Location: " + mFullName + "\nZipcode: " + mZip;
    }
}
