package android.fullsail.com.javaii_w1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Shaun on 11/30/2014.
 */
public class DisplayFragment extends Fragment {

    public static final String TAG = "DisplayFragment.TAG";
    public static DisplayFragment newInstance() {
        DisplayFragment frag = new DisplayFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {

        // Create and return view for this fragment.
        View view = _inflater.inflate(R.layout.display_fragment, _container, false);
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
