package android.fullsail.com.javaii_casestudy2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.fullsail.com.javaii_casestudy2.fragments.CurrentFragment;
import android.fullsail.com.javaii_casestudy2.fragments.HourlyFragment;
import android.fullsail.com.javaii_casestudy2.fragments.WeeklyFragment;
import android.os.AsyncTask;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 * Created by Shaun on 12/21/2014.
 */
public class TabAdapter extends FragmentPagerAdapter {



    final String TAG = "TABADAPTER";

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.i(TAG, "Position: " + position);


        switch(position) {

            case 0:
                return CurrentFragment.newInstance("CURRENT FORECAST");
            case 1:
                return HourlyFragment.newInstance("HOURLY FORECAST");
            case 2:
                return WeeklyFragment.newInstance("WEEKLY FORECAST");
            default: return CurrentFragment.newInstance("CURRENT FORECAST");
        }


    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }



}


