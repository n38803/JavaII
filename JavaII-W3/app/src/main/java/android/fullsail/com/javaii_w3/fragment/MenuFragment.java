package android.fullsail.com.javaii_w3.fragment;

import android.app.Fragment;
import android.fullsail.com.javaii_w3.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shaun on 12/14/2014.
 */
public class MenuFragment extends Fragment {

    public static final String TAG = "MENUFRAGMENT";

    public static MenuFragment newInstance() {
        MenuFragment frag = new MenuFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Create and return view for this fragment.
        View view = _inflater.inflate(R.layout.menu_fragment, _container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState) {
        super.onActivityCreated(_savedInstanceState);

        // Get our TextView and set some text to it.
        //TextView tv = (TextView)getView().findViewById(R.id.displayView);
        //tv.setText("Hello World!");
    }

}

