package android.fullsail.com.javaii_w4.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.fullsail.com.javaii_w4.MainActivity;
import android.fullsail.com.javaii_w4.R;
import android.fullsail.com.javaii_w4.dataclass.Item;
import android.fullsail.com.javaii_w4.dataclass.ItemAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Shaun on 12/18/2014.
 */
public class MainListFragment extends Fragment {

    private final String TAG = "List-Fragment";

    private ItemListener mListener;
    public ArrayList<Item> mItemList;
    private ActionMode mActionMode;
    public int mSelected;
    public boolean delete;



    public interface ItemListener{
        public ArrayList<Item> getItems();

    }

    public MainListFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof ItemListener) {
            mListener = (ItemListener) activity;
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

        ListView itemListView = (ListView) getView().findViewById(R.id.itemList);
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), mListener.getItems());
        itemListView.setAdapter(itemAdapter);


        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(mActionMode != null){
                    return false;
                }

                // assign position to constant
                mSelected = position;

                Log.i(TAG, "POSITION: " + mSelected);

                // call action bar
                mActionMode = getActivity().startActionMode(mActionModeCallback);

                return true;
            }
        });
    }

    public void updateListData(){
        ListView contactList = (ListView) getView().findViewById(R.id.itemList);
        BaseAdapter contactAdapter = (BaseAdapter) contactList.getAdapter();
        contactAdapter.notifyDataSetChanged();
    }

    // CONTEXTUAL ACTION BAR
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.actionbar_delete, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            // delete contact based on selection
            Log.i(TAG, "in action mode POSITION: " + mSelected);
            mItemList = mListener.getItems();

            // TODO - Set Delete dialog alert? Not required in rubric but if enough time try
            // String thisItem = mItemList.get(mSelected).toString();

            // delete item based on selection
            mItemList.remove(mSelected);

            return false;

        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //refresh display
            updateListData();

            // reset action mode
            mActionMode = null;
        }
    };

}
