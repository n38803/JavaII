package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.fullsail.com.javaii_w3.fragment.MainListFragment;
import android.fullsail.com.javaii_w3.fragment.MenuFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements MainListFragment.OnListClickListener {

    final String TAG = "DEBUGGING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ensure there is no saved instance & initiate fragment views
        if (savedInstanceState == null) {

            // menu view fragment
            MenuFragment menuFrag = MenuFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.menu_fragment, menuFrag, MainListFragment.TAG).commit();

            // list view fragment
            MainListFragment listFrag = MainListFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment, listFrag, MainListFragment.TAG).commit();

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
