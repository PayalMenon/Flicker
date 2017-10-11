package flickr.android.example.com.flickr;

import flickr.android.example.com.flickr.services.SearchService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application{

    public static final String BASE_URL = "https://api.flickr.com/services/rest/";

    private static SearchService mService;
    @Override

    public void onCreate() {
        super.onCreate();

        Retrofit builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = builder.create(SearchService.class);;
    }

    public static SearchService getSearchService() {
        return mService;
    }
}
