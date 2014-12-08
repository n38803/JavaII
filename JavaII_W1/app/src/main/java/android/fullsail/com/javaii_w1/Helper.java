package android.fullsail.com.javaii_w1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Shaun on 12/8/2014.
 */



public class Helper extends Activity {


    boolean connected;
    Context mContext;

    final String TAG = "HELPER CLASS";


    public Helper (Context mContext){
        this.mContext = mContext;
    }


    // Test network connectivity
    public boolean getConnection() {



        // Grab connectivity manager
        ConnectivityManager mgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get active network info
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        // determine what type of connections are available
        if(netInfo != null) {
            if(netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                connected = true;

            } else if(netInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                connected = true;

            }
            if(netInfo.isConnected()) {

                connected = true;

            }
        }
        else{

            connected = false;
        }

        return connected;



    }


    // Creates local storage file
    public void createFile (String name, String info) throws IOException{

        String fileName = (name + ".txt");
        String fileInfo = info;
        FileOutputStream fos = mContext.openFileOutput(fileName, mContext.MODE_PRIVATE);

        Toast fileSaved = Toast.makeText(mContext.getApplicationContext(), ("[ " + fileName + ".txt ] Saved."),
                Toast.LENGTH_SHORT);
        fileSaved.setGravity(Gravity.BOTTOM|Gravity.LEFT, 200, 800);
        fileSaved.show();


        fos.write(fileInfo.getBytes());
        fos.close();

    }

    // Retrieves local storage file
    public void readFile(String file) throws IOException{


        String fileName = (file + ".txt");
        FileInputStream fis = mContext.openFileInput(fileName);

        // conditional to ensure there is a valid file
        if(fis != null){
            BufferedInputStream bis = new BufferedInputStream(fis);
            StringBuffer b = new StringBuffer();
            while (bis.available() != 0) {
                char c = (char) bis.read();
                b.append(c);
            }

            // display text
            ((TextView) findViewById(R.id.titleView)).setText(file);
            ((TextView) findViewById(R.id.detailView)).setText(b.toString());

            bis.close();
            fis.close();
        }
        else{
            // initiate alert
            AlertDialog.Builder noStorage = new AlertDialog.Builder(this.mContext);

            // assign alert fields
            noStorage.setTitle("NO LOCAL STORAGE");
            noStorage.setMessage("You do not have data stored. Please connect to the internet and try again.");
            noStorage.setNeutralButton("Ok", null);

            AlertDialog storageDialog = noStorage.create();
            storageDialog.show();

            ((TextView) findViewById(R.id.titleView)).setText("No Data Available.");
        }

    }

}


