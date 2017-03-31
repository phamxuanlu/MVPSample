package a.mvpsample.photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import a.mvpsample.R;
import a.mvpsample.model.Photo;

/**
 * Created by pham.xuan.lu on 3/31/17.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private List<Photo> photos;
    private LayoutInflater inflater;
    private Context context;

    public PhotoAdapter(Context context, List<Photo> data) {
        this.photos = data;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_photo, parent, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.setPhoto(photo);
    }

    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView imgvPhoto;

        public void setPhoto(Photo photo) {
            Picasso
                .with(context)
                .load(photo.getPath())
                .into(imgvPhoto);
        }

        public PhotoHolder(View view) {
            super(view);
            imgvPhoto = (ImageView) view.findViewById(R.id.imgvPhoto);
        }
    }
}
