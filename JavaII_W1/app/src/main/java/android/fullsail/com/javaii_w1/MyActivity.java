package android.fullsail.com.javaii_w1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        // ensure there is no saved instance & initiate fragment views
        if(savedInstanceState == null) {
            MainListFragment listfrag = MainListFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.list_Fragment, listfrag, MainListFragment.TAG).commit();


            FragmentManager mgr = getFragmentManager();
            FragmentTransaction trans = mgr.beginTransaction();

            DisplayFragment frag = DisplayFragment.newInstance();
            trans.replace(R.id.display_fragment, frag, DisplayFragment.TAG);
            trans.commit();
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
