package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.content.Intent;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.fullsail.com.javaii_w3.fragment.DetailFragment;
import android.fullsail.com.javaii_w3.fragment.MainListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Shaun on 12/14/2014.
 */
public class DetailActivity extends Activity {

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
                    .add(R.id.detailContainer, new DetailFragment())
                    .commit();

        }

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            mContact = (Contact) detailIntent.getSerializableExtra(CONTACTEXTRA);
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






}
