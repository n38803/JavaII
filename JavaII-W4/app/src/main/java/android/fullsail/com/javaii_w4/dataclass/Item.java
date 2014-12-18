package android.fullsail.com.javaii_w4.dataclass;

/**
 * Created by Shaun on 12/18/2014.
 */
public class Item {


    private String mItem;


    public Item(){
        mItem = "";


    }

    public Item(String info){
        mItem = info;


    }

    public String getItem() {
        return mItem;
    }


    public void setItem(String mItem) {
        this.mItem = mItem;
    }


}
