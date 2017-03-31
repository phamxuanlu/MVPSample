package a.mvpsample.service;

import java.util.Map;

import a.mvpsample.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pham.xuan.lu on 3/31/17.
 */
public interface FuckService {
    @GET("photos.json")
    Call<Map<String, Photo>> getPhotos();
}
