package com.example.sagi.imagesearch.ui.image.list;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sagi.imagesearch.data.DataManager;
import com.example.sagi.imagesearch.data.model.ImageEntity;
import com.example.sagi.imagesearch.ui.base.BasePresenter;
import com.example.sagi.imagesearch.ui.base.ListScrollListener;
import com.example.sagi.imagesearch.ui.image.ImageState;
import com.example.sagi.imagesearch.util.Util;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

import static com.example.sagi.imagesearch.data.DataManager.INITIAL_IMAGE_SEARCH_PAGE_NUMBER;

/**
 * Created by sagiyemini on 28/03/2018.
 */

public class ImageListPresenter extends BasePresenter<ImageListMvpView> implements ListScrollListener.PaginationListener {

    private static final String TAG = "ImageListPresenter";
    private static final String SEARCH_TERM = "example";
    private static final int MIN_DISTANCE_FROM_END_OF_THE_LIST_TO_SYNC_NEXT_PAGE = 5;
    private int mLastPageSyncedSuccessfully;

    private final DataManager mDataManager;
    private final ImageState mImageState;

    private Disposable mGetImagesDisposable;
    private Disposable mSyncImagesDisposable;

    @Inject
    public ImageListPresenter(DataManager dataManager, ImageState imageState) {
        this.mDataManager = dataManager;
        this.mImageState = imageState;
    }

    @Override
    public void attachView(ImageListMvpView mvpView) {
        super.attachView(mvpView);

        syncImageListPage(INITIAL_IMAGE_SEARCH_PAGE_NUMBER);
        getImageList();
    }

    @Override
    public void detachView() {
        Util.dispose(mGetImagesDisposable);
        super.detachView();
    }

    private void syncImageListPage(int pageNumber) {
        // On first page stop current sync
        if (pageNumber == INITIAL_IMAGE_SEARCH_PAGE_NUMBER) {
            Util.dispose(mSyncImagesDisposable);
        }
        if (!isSyncInProgress()) {
            mSyncImagesDisposable = mDataManager.syncImagesPage(SEARCH_TERM, pageNumber)
                    .compose(Util.applySchedulers())
                    .subscribe(completed -> {
                                mLastPageSyncedSuccessfully = pageNumber;
                                Log.d(TAG, "Loaded image list page " + pageNumber);
                            },
                            error -> Log.e(TAG, "Failed to get image list " + error.getMessage(), error));
        }
    }

    private void getImageList() {
        Util.dispose(mGetImagesDisposable);
        mGetImagesDisposable = mDataManager.getImages(SEARCH_TERM)
                .compose(Util.applySchedulers())
                .subscribe(images -> {
                    if (isViewAttached()) getMvpView().displayImages(images);
                });
    }

    public void onImageClicked(@NonNull ImageEntity imageEntity) {
        mImageState.selectImage(imageEntity);
    }

    @Override
    public void onScrolled(int itemCount, int lastVisibleItemPosition, boolean isScrollingDown) {
        int distanceFromEndOfTheList = itemCount - lastVisibleItemPosition;
        if (isScrollingDown && distanceFromEndOfTheList < MIN_DISTANCE_FROM_END_OF_THE_LIST_TO_SYNC_NEXT_PAGE && !isSyncInProgress()) {
            syncImageListPage(mLastPageSyncedSuccessfully + 1);
        }
    }

    private boolean isSyncInProgress() {
        return mSyncImagesDisposable != null && !mSyncImagesDisposable.isDisposed();
    }
}
