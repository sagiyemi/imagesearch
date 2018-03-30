package com.example.sagi.imagesearch.ui.image.manager;

import com.example.sagi.imagesearch.ui.base.BasePresenter;
import com.example.sagi.imagesearch.ui.image.ImageState;
import com.example.sagi.imagesearch.util.Util;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by sagiyemini on 30/03/2018.
 */

public class ImageManagerPresenter extends BasePresenter<ImageManagerMvpView> {

    private final ImageState mImageState;
    private Disposable mSelectedImageIdDisposable;

    @Inject
    public ImageManagerPresenter(ImageState imageState) {
        this.mImageState = imageState;
    }

    @Override
    public void attachView(ImageManagerMvpView mvpView) {
        super.attachView(mvpView);

        listenToImageSelection();
    }

    @Override
    public void detachView() {
        Util.dispose(mSelectedImageIdDisposable);
        super.detachView();
    }

    private void listenToImageSelection() {
        mSelectedImageIdDisposable = mImageState.getSelectedImageId()
                .distinctUntilChanged()
                .compose(Util.applySchedulers())
                .map(ImageState.NO_IMAGE_SELECTED::equals)
                .subscribe(noImageSelected -> {
                    if (isViewAttached()) {
                        if (noImageSelected) {
                            getMvpView().displayImageList();
                        } else {
                            getMvpView().displayImageDetails();
                        }
                    }
                });
    }

    public boolean onBackPressed() {
        // If image selected remove selection (navigate back), else don't handle back
        if (mImageState.isImageSelected()) {
            mImageState.removeImageSelection();
            return true;
        } else {
            return false;
        }
    }
}
