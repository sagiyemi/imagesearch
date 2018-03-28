package com.example.sagi.imagesearch.ui;

import android.util.Log;

import com.example.sagi.imagesearch.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListPresenter extends BasePresenter<ImageListMvpView> {

    @Inject
    public ImageListPresenter() {

    }

    @Override
    public void attachView(ImageListMvpView mvpView) {
        super.attachView(mvpView);

        syncImageList();
    }

    private void syncImageList() {
        Log.d("ImageListPresenter", "syncImageList");
    }
}
