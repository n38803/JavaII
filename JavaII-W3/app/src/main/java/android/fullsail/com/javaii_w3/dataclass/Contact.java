package android.fullsail.com.javaii_w3.dataclass;

import java.io.Serializable;

/**
 * Created by Shaun on 12/14/2014.
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 8736847634070552888L;

    private String mName;
    private String mLocation;
    private String mEmail;

    public Contact(){
        mName = "";
        mLocation = "";
        mEmail = "";

    }

    public Contact(String name, String location, String email){
        mName = name;
        mLocation = location;
        mEmail = email;

    }

    public String getName() {
        return mName;
    }
    public String getLocation() {
        return mLocation;
    }
    public String getEmail() {
        return mEmail;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}
