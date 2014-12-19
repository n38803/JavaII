package android.fullsail.com.javaii_w4.fragment;

import android.app.Fragment;
import android.fullsail.com.javaii_w4.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shaun on 12/18/2014.
 */
public class AddFragment extends Fragment {


    private final String TAG = "AddFragment";




    public AddFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);







    }



}
