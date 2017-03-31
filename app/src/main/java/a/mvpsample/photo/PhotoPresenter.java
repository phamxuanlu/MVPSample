package a.mvpsample.photo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.mvpsample.model.Photo;
import a.mvpsample.service.FuckService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pham.xuan.lu on 3/31/17.
 */
public class PhotoPresenter implements PhotoContract.Presenter {
    private final FuckService service;
    private final PhotoContract.View view;

    public PhotoPresenter(FuckService service, PhotoContract.View view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void loadPhotos() {
        Call<Map<String, Photo>> callPhotos = service.getPhotos();
        callPhotos.enqueue(new Callback<Map<String, Photo>>() {
            @Override
            public void onResponse(Call<Map<String, Photo>> call,
                                   Response<Map<String, Photo>> response) {
                if (response.isSuccessful()) {
                    Map<String, Photo> body = response.body();
                    List<Photo> data = new ArrayList<Photo>();
                    for (String key : body.keySet()) {
                        data.add(body.get(key));
                    }
                    view.showPhotos(data);
                }
            }

            @Override
            public void onFailure(Call<Map<String, Photo>> call, Throwable t) {
                Log.e("LUPX", t.getMessage(), t);
            }
        });
    }

    @Override
    public void start() {
        loadPhotos();
    }
}
