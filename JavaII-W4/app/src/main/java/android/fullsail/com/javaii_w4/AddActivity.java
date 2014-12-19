package android.fullsail.com.javaii_w4;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.fullsail.com.javaii_w4.dataclass.Item;
import android.fullsail.com.javaii_w4.fragment.AddFragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Shaun on 12/18/2014.
 */
public class AddActivity extends Activity {

    public TextView inputItem;

    public String mItem;
    private ActionMode mActionMode;

    private ArrayList<Item> mItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Check to see whether or not there is a saved instance of the fragment
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AddFragment())
                    .commit();
        }

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Grabs intent from main activity
        Intent addIntent = getIntent();

        // verify there is an intent available to grab
        if (addIntent != null) {

            // do stuff after grabbing intent.
        }


    }

    public void onCancel(View v) {
        clearDisplay();
        finish();


    }

    public void onSave(View v) {
        inputItem = (TextView) findViewById(R.id.inputItem);


        // assign input to variables
        mItem = inputItem.getText().toString();


        // save variables to contact object
        //mContactDataList = new ArrayList<Contact>();
        // mContactDataList.add(new Contact(cName, cEmail, cLocation));

        Intent intent = new Intent();
        intent.putExtra("item", mItem);
        intent.putExtra("action", "add");
        setResult(RESULT_OK, intent);

        clearDisplay();
        finish();


    }

    private void clearDisplay() {

        inputItem = (TextView) findViewById(R.id.inputItem);
        inputItem.setText("");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        AlertDialog.Builder save = new AlertDialog.Builder(this);
        save.setTitle("Title of alert dialog");
        save.setIcon(android.R.drawable.ic_dialog_alert);

        // USER ELECTS TO SAVE INFORMATION
        save.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // assign view & input to constant
                inputItem = (TextView) findViewById(R.id.inputItem);
                mItem = inputItem.getText().toString();

                // pass information to intent
                Intent intent = new Intent();
                intent.putExtra("item", mItem);
                intent.putExtra("action", "add");
                setResult(RESULT_OK, intent);


                // TODO - CLEAR DISPLAY METHOD

                // close current activity
                finish();



            } });

        // USER ELECTS TO DISCARD INFORMATION
        save.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // TODO - CLEAR DISPLAY METHOD

                finish();


            } });
        save.show();

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


