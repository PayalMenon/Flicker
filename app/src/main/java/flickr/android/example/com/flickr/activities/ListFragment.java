package flickr.android.example.com.flickr.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import flickr.android.example.com.flickr.Application;
import flickr.android.example.com.flickr.Pojo.Photo;
import flickr.android.example.com.flickr.Pojo.PhotoResponse;
import flickr.android.example.com.flickr.Pojo.Photos;
import flickr.android.example.com.flickr.R;
import flickr.android.example.com.flickr.adapter.ListAdapter;
import flickr.android.example.com.flickr.services.SearchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements ListActivity.FragmentListener{

    @BindView(R.id.listView)
    RecyclerView mListView;

    private ListAdapter mAdapter;
    private GridLayoutManager mManager;

    private List<Photo> mPhotoList = new ArrayList<>();

    private int mPage;
    private String mQuery;
    private int mtotalPhotos;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mAdapter = new ListAdapter(mPhotoList);
        mListView.setAdapter(mAdapter);

        mManager = new GridLayoutManager(getActivity(), 3);
        mListView.setLayoutManager(mManager);

        ((ListActivity) getActivity()).setFragmentListener(this);

        mListView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosition = mManager.findLastCompletelyVisibleItemPosition();
                if((mPhotoList.size() < mtotalPhotos) && lastPosition == (mPhotoList.size() - 1)){
                    getSearchList(mQuery, mPage + 1);
                }
            }
        });
    }

    @Override
    public void clearFragmentList() {
        mPhotoList.clear();
        mAdapter.updateFeedList(mPhotoList);
    }

    @Override
    public void updateFragmentList(String query, int page) {
        mQuery = query;
        getSearchList(query, page);
    }

    public void getSearchList(String query, int page) {
        SearchService service = Application.getSearchService();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("text", query);
        queryMap.put("per_page", 20);
        queryMap.put("page", page);
        Call<PhotoResponse> call = service.getSearchImages(queryMap);
        call.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {

                if (response != null && response.isSuccessful()) {
                    PhotoResponse photoResponse = response.body();
                    mPage = photoResponse.getPhotos().getPage();
                    mtotalPhotos = photoResponse.getPhotos().getTotal();
                    mPhotoList.addAll(photoResponse.getPhotos().getPhoto());
                    mAdapter.updateFeedList(mPhotoList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {
                Log.d("Failed", "Failed");
            }
        });
    }

}
