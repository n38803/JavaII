package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.fullsail.com.javaii_w3.fragment.DetailFragment;
import android.fullsail.com.javaii_w3.fragment.MainListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Shaun on 12/14/2014.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener {

    private final String TAG = "DETAILACTIVITY";

    private Contact mContact;
    private int mDelete;

    public static final String CONTACTEXTRA = "android.fullsail.com.javaii_w3.Contact";
    public static final String DELETEEXTRA = "android.fullsail.com.javaii_w3.Delete";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }

        // Grabs intent from main activity
        Intent detailIntent = getIntent();

        // verify there is an intent available to grab
        if(detailIntent != null){

            // collect contact extra object
            mContact = (Contact) detailIntent.getSerializableExtra(CONTACTEXTRA);
            mDelete = detailIntent.getIntExtra(DELETEEXTRA, 0);
        }

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

    //INTERFACE METHODS

    public void onEmail(View v){

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { mContact.getEmail() });
        email.putExtra(Intent.EXTRA_SUBJECT, "For " + mContact.getName() + "'s Eyes Only!");
        email.putExtra(Intent.EXTRA_TEXT, "Hey " + mContact.getName()
                + ",\n\n I totally forgot that you are from " + mContact.getLocation()
                + ".  I was thinking that we should get together and catch up sometime!");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client"));
    }


    public Contact getContact() {
        return mContact;
    }


    public int getDelete(){
        return mDelete;
    }


    public void deleteContact() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.DELETECONTACTEXTRA, mDelete);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}