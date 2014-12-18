package android.fullsail.com.javaii_w4;

import android.app.Activity;
import android.fullsail.com.javaii_w4.dataclass.Item;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    private ArrayList<Item> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
}
