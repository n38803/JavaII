package android.fullsail.com.javaii_w1;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Shaun on 11/30/2014.
 */
public class MainListFragment extends ListFragment {

    public static final String TAG = "MainListFragment.TAG";
    public static MainListFragment newInstance() {
        MainListFragment frag = new MainListFragment();
        return frag;


    }


    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);



        // Create array from Strings.xml & Assign to adapter
        String[] testArray = getResources().getStringArray(R.array.testArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testArray);
        setListAdapter(adapter);


    }
    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long _id) {

        // grab string value from current clicked position & post in alert
        String test = (String)_l.getItemAtPosition(_position);
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.item)
                .setMessage(getString(R.string.selected, test))
                .setPositiveButton(R.string.ok, null)
                .show();
    }

}
