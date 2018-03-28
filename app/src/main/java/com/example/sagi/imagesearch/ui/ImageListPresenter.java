package com.example.sagi.imagesearch.ui;

import android.util.Log;

import com.example.sagi.imagesearch.data.DataManager;
import com.example.sagi.imagesearch.ui.base.BasePresenter;
import com.example.sagi.imagesearch.util.Util;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListPresenter extends BasePresenter<ImageListMvpView> {

    private static final String SEARCH_TERM = "example";
    private int mCurrentPage = 1;

    private final DataManager mDataManager;

    @Inject
    public ImageListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ImageListMvpView mvpView) {
        super.attachView(mvpView);

        syncImageList();
        getImageList();
    }

    private void syncImageList() {
        Log.d("ImageListPresenter", "syncImageList");
        mDataManager.syncImagesPage(SEARCH_TERM, mCurrentPage)
                .compose(Util.applySchedulers())
                .subscribe();
    }

    private void getImageList() {
        mDataManager.getImages(SEARCH_TERM)
                .compose(Util.applySchedulers())
                .subscribe(images -> {
                    Log.d("ImageListPresenter", "Images " + images);
                });

    }
}
