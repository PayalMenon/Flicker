package flickr.android.example.com.flickr.Pojo;

import com.google.gson.annotations.Expose;

public class Photo {

    @Expose
    String id;

    @Expose
    String owner;

    @Expose
    String secret;

    @Expose
    String server;

    @Expose
    int farm;

    @Expose
    String title;

    @Expose
    int ispublic;

    @Expose
    int isfriend;

    @Expose
    int isfamily;

    public int getFarm() {
        return farm;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public int getIspublic() {
        return ispublic;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getTitle() {
        return title;
    }
}
