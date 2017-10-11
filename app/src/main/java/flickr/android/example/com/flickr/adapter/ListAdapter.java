package flickr.android.example.com.flickr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import flickr.android.example.com.flickr.Pojo.Photo;
import flickr.android.example.com.flickr.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private List<Photo> mPhotoList;
    private Context mContext;

    public ListAdapter(List<Photo> list) {
        mPhotoList = list;
    }

    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Photo photo = mPhotoList.get(position);

        StringBuilder builder = new StringBuilder();
        builder.append("http://farm").
                append(photo.getFarm()).
                append(".static.flickr.com/").
                append(photo.getServer()).
                append("/").
                append(photo.getId()).
                append("_").
                append(photo.getSecret()).
                append(".jpg");

        Glide.with(mContext).
                load(builder.toString()).
                asBitmap().
                placeholder(R.drawable.ic_placeholder).
                into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    public void updateFeedList(List<Photo> list){
        mPhotoList = list;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        ImageView imageView;

        public ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
