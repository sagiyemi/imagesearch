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

    private final DataManager mDataManager;

    @Inject
    public ImageListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ImageListMvpView mvpView) {
        super.attachView(mvpView);

        syncImageList();
    }

    private void syncImageList() {
        Log.d("ImageListPresenter", "syncImageList");
        mDataManager.syncImageSearch()
                .compose(Util.applySchedulers())
                .subscribe();
    }
}
