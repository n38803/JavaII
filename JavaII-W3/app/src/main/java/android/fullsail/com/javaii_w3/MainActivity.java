package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.fullsail.com.javaii_w3.fragment.MainListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends Activity implements MainListFragment.ContactListener {

    final String TAG = "DEBUGGING";

    private ArrayList<Contact> mContactDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ensure there is no saved instance & initiate fragment views
        if (savedInstanceState == null) {

            // create fragment
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainListFragment())
                    .commit();



            /*
            // setup fragment manager for display view
            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();

            // display view fragment
            DisplayFragment displayfrag = DisplayFragment.newInstance();
            trans.replace(R.id.display_fragment, displayfrag, DisplayFragment.TAG);
            trans.commit();
            */

        }


        mContactDataList = new ArrayList<Contact>();
        mContactDataList.add(new Contact("Test 1","test@fullsail.com"));
        mContactDataList.add(new Contact("Test 2","test@fullsail.com"));
        mContactDataList.add(new Contact("Test 3","test@fullsail.com"));
        mContactDataList.add(new Contact("Test 4","test@fullsail.com"));
        mContactDataList.add(new Contact("Test 5","test@fullsail.com"));




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

    // TODO - CREATE RESULT HANDLING METHOD

    // INTERFACE METHODS

    public void viewContact (int position){
        //TODO - CREATE DETAIL INTENT
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.CONTACTEXTRA, mContactDataList.get(position));
        startActivity(detailIntent);

    }

    public void deleteContact (int position){
        //TODO - CREATE DETAIL INTENT

    }

    @Override
    public ArrayList<Contact> getContacts() {
        return mContactDataList;
    }


}
