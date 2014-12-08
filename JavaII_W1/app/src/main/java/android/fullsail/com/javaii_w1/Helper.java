package android.fullsail.com.javaii_w1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Shaun on 12/8/2014.
 */
public class Helper {

    public boolean connected;
    Context mContext;

    final String TAG = "HELPER CLASS";


    public Helper (Context mContext){
        this.mContext = mContext;
    }


    // Test network connectivity
    public boolean getConnection() {

        Log.d(TAG, "Connected: " + connected);

        // Grab connectivity manager
        ConnectivityManager mgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get active network info
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        return netInfo.isConnected();

    }



}
