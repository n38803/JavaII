package android.fullsail.com.javaii_w3.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.fullsail.com.javaii_w3.DetailActivity;
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
public class DetailFragment extends Fragment{

    private final String TAG = "DETAILFRAGMENT";

    private DetailListener mListener;

    public interface DetailListener{
        public Contact getContact();
        public int getDelete();
        public void deleteContact();
    }

    public DetailFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof DetailListener) {
            mListener = (DetailListener) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement DetailListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView nameView = (TextView) getView().findViewById(R.id.contactName);
        nameView.setText(mListener.getContact().getName());

        TextView emailView = (TextView) getView().findViewById(R.id.contactEmail);
        emailView.setText(mListener.getContact().getEmail());


        if(mListener.getDelete() > 0){
            Button deleteButton = (Button) getView().findViewById(R.id.deleteButton);
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.deleteContact();
                }
            });
        }
    }



}
