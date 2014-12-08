package android.fullsail.com.javaii_w1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

/**
 * Created by Shaun on 11/30/2014.
 */
public class MainListFragment extends ListFragment {

    public static final String TAG = "DEBUG";
    private OnListClickListener mListener;
    public static MainListFragment newInstance() {
        MainListFragment frag = new MainListFragment();
        return frag;




    }

    public interface OnListClickListener{
        public void displayText (String text) throws IOException;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof  OnListClickListener){
            mListener = (OnListClickListener) activity;

        } else {
            throw new IllegalArgumentException("Containing Activity must have OnListListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);



        // Create array from Strings.xml & Assign to adapter
        String[] cityArray = getResources().getStringArray(R.array.cityArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cityArray);
        setListAdapter(adapter);


    }
    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {

        // grab string value from current clicked position & post in alert
        String cityFromList = (String)_l.getItemAtPosition(_position);
        Log.e(TAG, "CLICKED: " + cityFromList);
        try {
            mListener.displayText(cityFromList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
