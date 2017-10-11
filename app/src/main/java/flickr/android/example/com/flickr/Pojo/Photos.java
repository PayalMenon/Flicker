package flickr.android.example.com.flickr.Pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Photos {

    @Expose
    int page;

    @Expose
    int pages;

    @Expose
    int perpage;

    @Expose
    int total;

    List<Photo> photo;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public int getTotal() {
        return total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }
}
