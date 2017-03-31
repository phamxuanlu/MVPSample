package a.mvpsample.photo;

import java.util.List;

import a.mvpsample.BasePresenter;
import a.mvpsample.BaseView;
import a.mvpsample.model.Photo;

/**
 * Created by pham.xuan.lu on 3/31/17.
 */
public class PhotoContract {
    interface View extends BaseView<Presenter> {
        void showPhotos(List<Photo> photos);
    }

    interface Presenter extends BasePresenter {
        void loadPhotos();
    }
}
