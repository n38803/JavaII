package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.fullsail.com.javaii_w3.fragment.MainListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity implements MainListFragment.ContactListener {

    private final String TAG = "MAINACTIVITY";

    public static final int DELETEREQUEST = 1;
    public static final String DELETECONTACTEXTRA = "android.fullsail.com.javaii_w3.Delete";

    private ArrayList<Contact> mContactDataList;
    private Button addButton;

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
        mContactDataList = new ArrayList<Contact>();
        mContactDataList.add(new Contact("Position 0", "test@test.com"));
        mContactDataList.add(new Contact("Position 1", "test@test.com"));
        mContactDataList.add(new Contact("Position 2", "test@test.com"));
        mContactDataList.add(new Contact("Position 3", "test@test.com"));
        mContactDataList.add(new Contact("Position 4", "test@test.com"));

        addButton = (Button) findViewById(R.id.addButton);





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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK && requestCode == DELETEREQUEST){
            mContactDataList.remove(data.getIntExtra(DELETECONTACTEXTRA,0));
            MainListFragment mf = (MainListFragment) getFragmentManager().findFragmentById(R.id.container);
            mf.updateListData();
        }
    }






    //INTERFACE METHODS



    @Override
    public void viewContact(int position){

        // Declare Intent
        Intent detailIntent = new Intent(this, DetailActivity.class);

        // pass position from list into intent by using constant from detail activity
        detailIntent.putExtra(DetailActivity.CONTACTEXTRA, mContactDataList.get(position));

        // start detail activity by passing intent we wish to load
        startActivity(detailIntent);
    }

    @Override
    public void deleteContact(int position){
        Intent deleteIntent = new Intent(this, DetailActivity.class);
        deleteIntent.putExtra(DetailActivity.CONTACTEXTRA, mContactDataList.get(position));
        deleteIntent.putExtra(DetailActivity.DELETEEXTRA, position);
        startActivityForResult(deleteIntent, DELETEREQUEST);
    }

    @Override
    public ArrayList<Contact> getContacts() {
        return mContactDataList;
    }
}
