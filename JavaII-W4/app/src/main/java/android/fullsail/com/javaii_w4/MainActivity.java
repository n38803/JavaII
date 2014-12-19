package android.fullsail.com.javaii_w4;

import android.app.Activity;
import android.content.Intent;
import android.fullsail.com.javaii_w4.dataclass.Item;
import android.fullsail.com.javaii_w4.fragment.MainListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends Activity implements MainListFragment.ContactListener {

    private final String TAG = "MainActivity";

    private ArrayList<Item> mItemList;
    public Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewContact(int position){

        // TODO - ACTION UPON ITEM CLICK
    }

    public void deleteContact(int position){

        // TODO - delete item selected
    }

    public void onClick(View v){

        Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
        //addIntent.putExtra("contactName", mContactDataList.get());
        //startActivityForResult(addIntent, ADDREQUEST);
        startActivity(addIntent);


    }

    //@Override
    public ArrayList<Item> getItems() {
        return mItemList;
    }
}
