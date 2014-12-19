package android.fullsail.com.javaii_w4;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.fullsail.com.javaii_w4.dataclass.Item;
import android.fullsail.com.javaii_w4.fragment.MainListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements MainListFragment.ItemListener {

    private final String TAG = "MainActivity";
    public final int ADDREQUEST = 1;

    private ArrayList<Item> mItemList;
    public Button addButton;
    private int mItemSelected = -1;

    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Check to see whether or not there is a saved instance of the fragment
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainListFragment())
                    .commit();
        }




        // static population of data
        mItemList = new ArrayList<Item>();
        mItemList.add(new Item("Random Info 0"));
        mItemList.add(new Item("Random Info 1"));
        mItemList.add(new Item("Random Info 2"));
        mItemList.add(new Item("Random Info 3"));
        mItemList.add(new Item("Random Info 4"));





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(addIntent, ADDREQUEST);
        //startActivity(addIntent);

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // INTENT HANDLER
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == ADDREQUEST && resultCode == RESULT_OK){
            String requestInfo = data.getStringExtra("item");
            String action = data.getStringExtra("action");

            // add item to list
            mItemList.add(new Item(requestInfo));

            // update display
            MainListFragment nf = (MainListFragment) getFragmentManager().findFragmentById(R.id.container);
            nf.updateListData();


            if (action.equals("add")){
                Toast.makeText(this, requestInfo + " added to List.", Toast.LENGTH_LONG).show();


            }
        }
    }




    // INTERFACE METHOD
    public ArrayList<Item> getItems() {
        return mItemList;
    }
}
