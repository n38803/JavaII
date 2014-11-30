package android.fullsail.com.javaii_w1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Shaun on 11/30/2014.
 */
public class CustomConnectivityManager {

    final String TAG = "CONNECTION CHECK";
    Context context;
    Boolean connected;

    boolean test;


    public boolean getConnection() {


        // Getting our connectivity manager.
        ConnectivityManager mgr = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Getting our active network information.
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        // check for connection
        if (netInfo != null) {

            // verify DATA connection
            if (netInfo.isConnected()) {
                connected = true;
                Log.i(TAG, "Connected: " + connected);

            } else {
                connected = false;
                Log.e(TAG, "Connected: " + connected);
            }
        }

        return connected;

    }
}

