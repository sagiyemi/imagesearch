package com.example.sagi.imagesearch.ui.image.details;

import com.example.sagi.imagesearch.data.DataManager;
import com.example.sagi.imagesearch.ui.base.BasePresenter;
import com.example.sagi.imagesearch.ui.image.ImageState;
import com.example.sagi.imagesearch.util.Util;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by sagiyemini on 30/03/2018.
 */

public class ImageDetailsPresenter extends BasePresenter<ImageDetailsMvpView> {

    private final DataManager mDataManager;
    private final ImageState mImageState;

    private Disposable mGetImageDisposable;

    @Inject
    public ImageDetailsPresenter(DataManager dataManager, ImageState imageState) {
        this.mDataManager = dataManager;
        this.mImageState = imageState;
    }

    @Override
    public void attachView(ImageDetailsMvpView mvpView) {
        super.attachView(mvpView);

        getImage();
    }

    @Override
    public void detachView() {
        Util.dispose(mGetImageDisposable);
        super.detachView();
    }

    private void getImage() {
        mGetImageDisposable = mImageState.getSelectedImageId()
                .switchMap(mDataManager::getImage)
                .compose(Util.applySchedulers())
                .subscribe(selectedImageEntity -> {
                    if (isViewAttached()) getMvpView().displayImage(selectedImageEntity);
                });
    }
}
