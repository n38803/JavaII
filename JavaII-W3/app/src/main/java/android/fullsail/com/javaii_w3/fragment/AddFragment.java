package android.fullsail.com.javaii_w3.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.fullsail.com.javaii_w3.R;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Shaun on 12/14/2014.
 */
public class AddFragment extends Fragment {



    private final String TAG = "ADDFRAGMENT";




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
