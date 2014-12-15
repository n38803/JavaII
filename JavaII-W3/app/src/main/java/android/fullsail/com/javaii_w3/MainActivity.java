package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.fullsail.com.javaii_w3.fragment.MainListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends Activity implements MainListFragment.ContactListener {

    private final String TAG = "MAINACTIVITY";
    private final String contactFile = "JavaIIContacts.txt";


    public static final int DELETEREQUEST = 1;
    public static final String DELETECONTACTEXTRA = "android.fullsail.com.javaii_w3.Delete";
    public static final int ADDREQUEST = 2;

    private ArrayList<Contact> mContactDataList;
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



        readFile();

        addButton = (Button) findViewById(R.id.addButton);





    }


    public void onClick(View v){

        Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
        //addIntent.putExtra("contactName", mContactDataList.get());
        startActivityForResult(addIntent, ADDREQUEST);
        //startActivity(addIntent);


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

            writeFile();
        }

        if(requestCode == ADDREQUEST && resultCode == RESULT_OK){
            String rName = data.getStringExtra("contactName");
            String rLocation = data.getStringExtra("contactLocation");
            String rEmail = data.getStringExtra("contactEmail");
            String action = data.getStringExtra("action");

            mContactDataList.add(new Contact(rName, rLocation, rEmail));


            MainListFragment nf = (MainListFragment) getFragmentManager().findFragmentById(R.id.container);
            nf.updateListData();

            writeFile();

            if (action.equals("add")){
                Toast.makeText(this, rName + " added to Contacts.", Toast.LENGTH_LONG).show();


            }
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

    // Creates local storage file
    private void writeFile() {

        try {
            FileOutputStream fos = openFileOutput(contactFile, this.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mContactDataList);
            Log.i(TAG, "Object Saved Successfully");
            oos.close();

        } catch (Exception e) {
            Log.e(TAG, "Save Unsuccessful");
        }


    }

    private void readFile() {


        try {
            FileInputStream fin = openFileInput(contactFile);
            ObjectInputStream oin = new ObjectInputStream(fin);
            mContactDataList = (ArrayList<Contact>) oin.readObject();
            oin.close();

        } catch(Exception e) {
            Log.e(TAG, "There are no files to pull");

            Toast.makeText(this, "No data Saved - Static information Populated.", Toast.LENGTH_LONG).show();

            // static population of data
            mContactDataList = new ArrayList<Contact>();
            mContactDataList.add(new Contact("Freddie Stetser", "Stratford, NJ", "freddie@joke.com"));
            mContactDataList.add(new Contact("George Williams", "Milford, DE", "george@test.com"));
            mContactDataList.add(new Contact("Ray Thompson", "Philadelphia, PA", "ray@123.com"));
            mContactDataList.add(new Contact("Steven Enstride", "Marlton, NJ", "steve@enstride.com"));
            mContactDataList.add(new Contact("John Smith", "Nowhere, USA", "johnsmith@mostwanted.com"));
        }
    }

}
