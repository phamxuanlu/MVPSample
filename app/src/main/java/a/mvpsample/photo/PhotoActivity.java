package a.mvpsample.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import a.mvpsample.R;
import a.mvpsample.model.Photo;
import a.mvpsample.service.RESTClient;

/**
 * Created by pham.xuan.lu on 3/31/17.
 */
public class PhotoActivity extends AppCompatActivity implements PhotoContract.View {
    private PhotoContract.Presenter presenter;
    private RecyclerView rcvPhotos;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        rcvPhotos = (RecyclerView) findViewById(R.id.rcvPhotos);
        adapter = new PhotoAdapter(this, null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvPhotos.setLayoutManager(gridLayoutManager);
        rcvPhotos.setAdapter(adapter);
        presenter = new PhotoPresenter(RESTClient.getInstance().getService(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(PhotoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showPhotos(List<Photo> photos) {
        adapter.setPhotos(photos);
    }
}
