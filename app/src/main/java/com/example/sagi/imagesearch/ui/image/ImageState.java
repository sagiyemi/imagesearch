package com.example.sagi.imagesearch.ui.image;

import android.support.annotation.NonNull;

import com.example.sagi.imagesearch.data.model.ImageEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by sagiyemini on 30/03/2018.
 */

@Singleton
public final class ImageState {

    public static final String NO_IMAGE_SELECTED = "NO_IMAGE_SELECTED";

    private final BehaviorSubject<String> mSelectedImageId;

    @Inject
    public ImageState() {
        this.mSelectedImageId = BehaviorSubject.createDefault(NO_IMAGE_SELECTED);
    }

    public void selectImage(@NonNull ImageEntity selectedImageEntity) {
        mSelectedImageId.onNext(selectedImageEntity.link());
    }

    public void removeImageSelection() {
        mSelectedImageId.onNext(NO_IMAGE_SELECTED);
    }

    public Observable<String> getSelectedImageId() {
        return mSelectedImageId;
    }

}
