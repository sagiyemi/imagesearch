package com.example.sagi.imagesearch.ui.imagelist;

import android.util.Log;

import com.example.sagi.imagesearch.data.DataManager;
import com.example.sagi.imagesearch.ui.base.BasePresenter;
import com.example.sagi.imagesearch.util.Util;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListPresenter extends BasePresenter<ImageListMvpView> {

    private static final String SEARCH_TERM = "example";
    private static final int INITIAL_PAGE_NUMBER = 1;
    private int mCurrentPage = 1;

    private final DataManager mDataManager;

    private Disposable mGetImagesDisposable;

    @Inject
    public ImageListPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ImageListMvpView mvpView) {
        super.attachView(mvpView);

        syncImageListPage(INITIAL_PAGE_NUMBER);
        getImageList();
    }

    @Override
    public void detachView() {
        Util.dispose(mGetImagesDisposable);
        super.detachView();
    }

    private void syncImageListPage(int pageNumber) {
        Log.d("ImageListPresenter", "syncImageListPage");
        mDataManager.syncImagesPage(SEARCH_TERM, pageNumber)
                .compose(Util.applySchedulers())
                .subscribe();
    }

    private void getImageList() {
        Util.dispose(mGetImagesDisposable);
        mGetImagesDisposable = mDataManager.getImages(SEARCH_TERM)
                .compose(Util.applySchedulers())
                .subscribe(images -> {
                    if (isViewAttached()) getMvpView().displayImages(images);
                });
    }
}
