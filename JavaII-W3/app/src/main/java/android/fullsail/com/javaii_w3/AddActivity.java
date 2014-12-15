package android.fullsail.com.javaii_w3;

import android.app.Activity;
import android.content.Intent;
import android.fullsail.com.javaii_w3.dataclass.Contact;
import android.fullsail.com.javaii_w3.fragment.AddFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Shaun on 12/14/2014.
 */
public class AddActivity extends Activity  {

    public TextView inputName;
    public TextView inputEmail;
    public TextView inputLocation;

    public String cName;
    public String cEmail;
    public String cLocation;

    private ArrayList<Contact> mContactDataList;


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

        // Grabs intent from main activity
        Intent addIntent = getIntent();

        // verify there is an intent available to grab
        if(addIntent != null){

            // do stuff after grabbing intent.
        }




    }

    public void onCancel(View v){
        clearDisplay();
        finish();


    }

    public void onSave(View v){
        inputName = (TextView) findViewById(R.id.inputName);
        inputEmail = (TextView) findViewById(R.id.inputEmail);
        inputLocation = (TextView) findViewById(R.id.inputLocation);

        // assign input to variables
        cName = inputName.getText().toString();
        cEmail = inputEmail.getText().toString();
        cLocation = inputLocation.getText().toString();

        // save variables to contact object
        //mContactDataList = new ArrayList<Contact>();
       // mContactDataList.add(new Contact(cName, cEmail, cLocation));

        Intent intent = new Intent();
        intent.putExtra("contactName", cName);
        intent.putExtra("contactLocation", cLocation);
        intent.putExtra("contactEmail", cEmail);
        intent.putExtra("action", "add");
        setResult(RESULT_OK, intent);

        clearDisplay();
        finish();



    }

    private void clearDisplay(){
        inputName = (TextView) findViewById(R.id.inputName);
        inputEmail = (TextView) findViewById(R.id.inputEmail);
        inputLocation = (TextView) findViewById(R.id.inputLocation);

        inputName.setText("");
        inputEmail.setText("");
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