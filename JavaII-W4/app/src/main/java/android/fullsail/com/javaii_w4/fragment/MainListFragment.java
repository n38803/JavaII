package android.fullsail.com.javaii_w4.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.fullsail.com.javaii_w4.R;
import android.fullsail.com.javaii_w4.dataclass.Item;
import android.fullsail.com.javaii_w4.dataclass.ItemAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Shaun on 12/18/2014.
 */
public class MainListFragment extends Fragment {

    private final String TAG = "List-Fragment";

    private ContactListener mListener;
    private ArrayList<Item> mItemList;

    public interface ContactListener{
        public void viewContact(int position);
        public void deleteContact(int position);
        public ArrayList<Item> getItems();
    }

    public MainListFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof ContactListener) {
            mListener = (ContactListener) activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement ContactListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView contactListView = (ListView) getView().findViewById(R.id.itemList);
        ItemAdapter contactAdapter = new ItemAdapter(getActivity(), mListener.getItems());
        contactListView.setAdapter(contactAdapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.viewContact(position);
            }
        });

        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.deleteContact(position);
                return true;
            }
        });
    }

    public void updateListData(){
        ListView contactList = (ListView) getView().findViewById(R.id.itemList);
        BaseAdapter contactAdapter = (BaseAdapter) contactList.getAdapter();
        contactAdapter.notifyDataSetChanged();
    }

}
