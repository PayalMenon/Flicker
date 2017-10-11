package flickr.android.example.com.flickr.services;

import java.util.Map;

import flickr.android.example.com.flickr.Pojo.PhotoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchService {

    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1")
    Call<PhotoResponse> getSearchImages(@QueryMap Map<String, Object> queryOptions);
}
