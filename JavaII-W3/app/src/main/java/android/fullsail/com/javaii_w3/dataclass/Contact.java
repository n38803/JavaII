package android.fullsail.com.javaii_w3.dataclass;

import java.io.Serializable;

/**
 * Created by Shaun on 12/14/2014.
 */
public class Contact implements Serializable {

    private String mName;
    private String mEmail;

    public Contact(){
        mName = "";
        mEmail = "";

    }

    public Contact(String first, String email){
        mName = first;
        mEmail = email;

    }

    public String getName() {
        return mName;
    }
    public String getEmail() {
        return mEmail;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}
